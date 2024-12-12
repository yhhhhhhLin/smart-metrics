package xyz.linyh.datasource.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.linyh.common.context.UserIdContext;
import xyz.linyh.datasource.client.DatasourceClient;
import xyz.linyh.datasource.client.MysqlDatasourceClient;
import xyz.linyh.datasource.factory.DatasourceClientFactory;
import xyz.linyh.datasource.mapper.DscInfoMapper;
import xyz.linyh.datasource.model.dto.DscAddOrUpdateDto;
import xyz.linyh.datasource.model.dto.DscQueryDto;
import xyz.linyh.datasource.model.entity.DscInfo;
import xyz.linyh.datasource.model.vo.DscInfoVO;
import xyz.linyh.datasource.service.DscInfoService;
import xyz.linyh.datasource.utils.PageResultUtil;

import java.util.Arrays;

/**
 * @author linzz
 * @description 针对表【dsc_info(数据源表)】的数据库操作Service实现
 * @createDate 2024-12-08 21:28:19
 */
@Service
public class DscInfoServiceImpl extends ServiceImpl<DscInfoMapper, DscInfo>
        implements DscInfoService {

    public static final String DSC_PASSWORD_SLAT = "1234712341234123";

    @Override
    public Boolean addDsc(DscAddOrUpdateDto dto) {
        String url = dto.getUrl();
        String username = dto.getUsername();
        String password = dto.getPassword();
        Integer datasourceTypeCode = dto.getDatasourceTypeCode();
        String datasourceName = dto.getDatasourceName();
        String datasourceDesc = dto.getDatasourceDesc();

        MysqlDatasourceClient client = (MysqlDatasourceClient) DatasourceClientFactory.getClient(datasourceTypeCode, url, username, password);
//        可以连接成功后续才可以添加到数据库
        client.testConnection();

        Long userId = UserIdContext.getUserId();
        DscInfo dscInfo = new DscInfo();
        dscInfo.setDscName(datasourceName);
        dscInfo.setDscType(datasourceTypeCode);
        dscInfo.setUrl(url);
        dscInfo.setUsername(username);


        AES aes = SecureUtil.aes(DSC_PASSWORD_SLAT.getBytes());
        dscInfo.setPassword(Arrays.toString(aes.encrypt(password)));
        dscInfo.setDatabaseName(client.getDatabaseName());
        dscInfo.setDscDesc(datasourceDesc);
        dscInfo.setConnectionParams(null);
        dscInfo.setDscStatus(1);
        dscInfo.setCreatedUserId(userId);
        dscInfo.setUpdatedBy(userId);

        return this.save(dscInfo);
    }

    @Override
    public Page<DscInfoVO> pageDscInfo(DscQueryDto dto) {

        Page<DscInfo> dscInfos = lambdaQuery()
                .eq(dto.getDscTypeCode() != null || dto.getDscTypeCode() != 0, DscInfo::getDscType, dto.getDscTypeCode())
                .eq(dto.getDscStatus() != null, DscInfo::getDscStatus, dto.getDscStatus())
                .like(StringUtils.isNotBlank(dto.getDscName()), DscInfo::getDscName, dto.getDscName())
                .like(StringUtils.isNotBlank(dto.getDscDesc()), DscInfo::getDscDesc, dto.getDscDesc())
                .page(new Page<>(dto.getCurrentPage(), dto.getPageSize()));

        return PageResultUtil.transfer(dscInfos, dscInfo -> {

            Long createdUserId = dscInfo.getCreatedUserId();
            Long updatedByUserId = dscInfo.getUpdatedBy();
            DscInfoVO dscinfoVO = new DscInfoVO();
//            TODO:
//            dscinfoVO.setCreatedUserName();
//            dscinfoVO.setUpdatedUserName();


            BeanUtils.copyProperties(dscInfo, dscinfoVO);
            return dscinfoVO;
        });

    }

    @Override
    public DscInfoVO getOneDscInfo(Long dscId) {
        DscInfo dscInfo = getById(dscId);
        DscInfoVO dscInfoVO = new DscInfoVO();
        BeanUtils.copyProperties(dscInfo,dscInfoVO);
//        TODO:
        Long createdUserId = dscInfo.getCreatedUserId();
        Long updatedByUserId = dscInfo.getUpdatedBy();
//        dscInfoVO.setCreatedUserName();
//        dscInfoVO.setUpdatedUserName();
        return dscInfoVO;
    }

    @Override
    public Boolean updateDscInfo(DscAddOrUpdateDto dto) {
//        如果密码有值，那么就是更新了密码
        String password = dto.getPassword();
        String datasourceDesc = dto.getDatasourceDesc();
        String datasourceName = dto.getDatasourceName();
        String username = dto.getUsername();
        String url = dto.getUrl();
        return lambdaUpdate()
                .eq(DscInfo::getId, dto.getDscId())
                .set(DscInfo::getDscDesc, datasourceDesc)
                .set(DscInfo::getDatabaseName, datasourceName)
                .set(DscInfo::getUsername, username)
                .set(DscInfo::getUrl, url)
                .set(StringUtils.isNotBlank(password), DscInfo::getPassword, SecureUtil.aes(DSC_PASSWORD_SLAT.getBytes()).encrypt(password))
                .update();
    }
}




