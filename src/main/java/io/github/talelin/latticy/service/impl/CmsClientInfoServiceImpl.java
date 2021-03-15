package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.dto.ClientDTO;
import io.github.talelin.latticy.mapper.CmsClientInterestMapper;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import io.github.talelin.latticy.mapper.CmsClientInfoMapper;
import io.github.talelin.latticy.model.CmsClientInterestDO;
import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.service.CmsClientInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.service.CmsClientInterestService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-13
 */
@Service
public class CmsClientInfoServiceImpl extends ServiceImpl<CmsClientInfoMapper, CmsClientInfoDO> implements CmsClientInfoService {

    @Autowired
    CmsClientInfoMapper clientInfoMapper;

    @Autowired
    CmsClientInterestMapper clientInterestMapper;

    @Autowired
    CmsClientInterestService clientInterestService;

    @Override
    public boolean create(ClientDTO validator) {
        CmsClientInfoDO cmsClientInfoDO = new CmsClientInfoDO();
        BeanUtils.copyProperties(validator, cmsClientInfoDO);

        if(!validator.getIsInPublicSea()) {
            UserDO localUser = LocalUser.getLocalUser();
            cmsClientInfoDO.setOwnedBy(localUser.getId());
        }

        // save user
        clientInfoMapper.insert(cmsClientInfoDO);

        // save interests
        for (CmsClientInterestDO interestProduct : validator.getInterestProducts()) {
            CmsClientInterestDO cmsClientInterestDO = new CmsClientInterestDO();
            BeanUtils.copyProperties(interestProduct, cmsClientInterestDO);
            cmsClientInterestDO.setClientId(cmsClientInfoDO.getId());

            clientInterestMapper.insert(cmsClientInterestDO);
        }

        return true;
    }

    @Override
    public void update(Long id, ClientDTO validator) {
        CmsClientInfoDO cmsClientInfoDO = new CmsClientInfoDO();
        BeanUtils.copyProperties(validator, cmsClientInfoDO);
        cmsClientInfoDO.setId(id);

        if(validator.getIsInPublicSea()) {
            cmsClientInfoDO.setOwnedBy(0l);
        }

        clientInfoMapper.updateById(cmsClientInfoDO);

        // delete original interests
        clientInterestService.deleteInterestByClientId(id);


        // update client interest
        for (CmsClientInterestDO interestProduct : validator.getInterestProducts()) {
            CmsClientInterestDO cmsClientInterestDO = new CmsClientInterestDO();
            BeanUtils.copyProperties(interestProduct, cmsClientInterestDO);
            cmsClientInterestDO.setClientId(cmsClientInfoDO.getId());

            clientInterestMapper.insert(cmsClientInterestDO);
        }
    }

    @Override
    public void deleteClient(Long id) {
        clientInfoMapper.deleteById(id);
    }

    @Override
    public ClientDTO getClientWithDetail(Long id) {
        ClientDTO clientDTO = new ClientDTO();

        CmsClientInfoDO cmsClientInfoDO = clientInfoMapper.selectById(id);
        BeanUtils.copyProperties(cmsClientInfoDO, clientDTO);

        if(cmsClientInfoDO.getOwnedBy() != null) {
            clientDTO.setIsInPublicSea(false);
        } else {
            clientDTO.setIsInPublicSea(true);
        }

        // get client interests
        clientDTO.setInterestProducts(clientInterestService.getByClientId(id));
        return clientDTO;
    }

}
