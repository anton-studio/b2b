package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.ClientDTO;
import io.github.talelin.latticy.mapper.CmsClientInterestMapper;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.mapper.CmsClientInfoMapper;
import io.github.talelin.latticy.service.CmsClientInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.service.CmsClientInterestService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // validate email
        if (!this.isEmailValid(validator.getEmail())) {
            return false;
        }

        // validate code
        if (!this.isCodeValid(validator.getCode())) {
            return false;
        }

        // save user
        int insertCount = clientInfoMapper.insert(cmsClientInfoDO);

        // save interests
        if (validator.getInterestProducts() != null) {
            for (CmsClientInterestDO interestProduct : validator.getInterestProducts()) {
                CmsClientInterestDO cmsClientInterestDO = new CmsClientInterestDO();
                BeanUtils.copyProperties(interestProduct, cmsClientInterestDO);
                cmsClientInterestDO.setClientId(cmsClientInfoDO.getId());

                clientInterestMapper.insert(cmsClientInterestDO);
            }
        }

        return insertCount == 1;
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

        if(cmsClientInfoDO.getOwnedBy() != null && cmsClientInfoDO.getOwnedBy() != 0l) {
            clientDTO.setIsInPublicSea(false);
        } else {
            clientDTO.setIsInPublicSea(true);
        }

        // get client interests
        clientDTO.setInterestProducts(clientInterestService.getByClientId(id));
        return clientDTO;
    }

    @Override
    public List<CmsClientInfoDO> getPublicSeaClients() {
        return clientInfoMapper.getPublicSeaClient();
    }

    @Override
    public IPage<CmsClientInfoDO> getPageWithFilter(Long page, Long count, Map<String, Object> params) {
        Page<CmsClientInfoDO> pager = new Page<>(page, count);
        if (StringUtils.hasText((String) params.get("sort"))) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn((String) params.get("sort"));
            orderItem.setAsc(!"DESC".equals(params.get("order")));
            pager.addOrder(orderItem);
        }
        IPage<CmsClientInfoDO> clientPage = clientInfoMapper.selectWithFilter(pager, params);
        return clientPage;
    }

    @Override
    public Boolean isEmailValid(String email) {
        return clientInfoMapper.countByEmail(email) == 0;
    }

    @Override
    public Boolean isCodeValid(String code) {
        return clientInfoMapper.countByCode(code) == 0;
    }

    @Override
    public Map<String, List<ClientDTO>> importAll(List<ClientDTO> validator) {
        List<ClientDTO> successList = new ArrayList<>();
        List<ClientDTO> failList = new ArrayList<>();
        for (ClientDTO clientDTO : validator) {
            if (this.create(clientDTO)) {
                successList.add(clientDTO);
            } else {
                failList.add(clientDTO);
            }
        }
        Map<String, List<ClientDTO>> map = new HashMap<>();
        map.put("successList", successList);
        map.put("failList", failList);
        return map;
    }

}
