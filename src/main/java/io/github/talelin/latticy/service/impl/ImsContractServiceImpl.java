package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.mapper.ImsContractProductMapper;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.mapper.ImsContractMapper;
import io.github.talelin.latticy.service.CmsClientInfoService;
import io.github.talelin.latticy.service.ImsContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.service.PmsSkuInfoService;
import io.github.talelin.latticy.service.PmsSpuInfoService;
import io.github.talelin.latticy.vo.PrintDataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-17
 */
@Service
public class ImsContractServiceImpl extends ServiceImpl<ImsContractMapper, ImsContractDO> implements ImsContractService {

    @Autowired
    ImsContractMapper contractMapper;

    @Autowired
    ImsContractProductMapper contractProductMapper;

    @Autowired
    CmsClientInfoService clientInfoService;

    @Autowired
    PmsSkuInfoService skuInfoService;

    @Autowired
    PmsSpuInfoService spuInfoService;

    @Override
    public void createContract(ContractDTO validator) {
        ImsContractDO imsContractDO = new ImsContractDO();
        BeanUtils.copyProperties(validator, imsContractDO);

        this.getBaseMapper().insert(imsContractDO);

        for (ImsContractProductDO spuId : validator.getSkus()) {
            ImsContractProductDO imsContractProductDO = new ImsContractProductDO();
            BeanUtils.copyProperties(spuId, imsContractProductDO);
            imsContractProductDO.setContractId(imsContractDO.getId());
            contractProductMapper.insert(imsContractProductDO);
        }
    }

    @Override
    public ContractDTO getContractDetail(Long id) {
        ImsContractDO imsContractDO = this.contractMapper.selectById(id);
        QueryWrapper<ImsContractProductDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ImsContractProductDO::getContractId, id);
        List<ImsContractProductDO> imsContractProductDOS = contractProductMapper.selectList(wrapper);
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(imsContractDO, contractDTO);
        contractDTO.setSkus(imsContractProductDOS);
        return contractDTO;
    }

    @Override
    public PrintDataVO getDataForPrint(Long id) {
        PrintDataVO result = new PrintDataVO();

        // get contract
        ImsContractDO imsContractDO = this.getBaseMapper().selectById(id);
        BeanUtils.copyProperties(imsContractDO, result);

        // get client
        CmsClientInfoDO cmsClientInfoDO = this.clientInfoService.getBaseMapper().selectById(imsContractDO.getClientId());
        result.setClientInfo(cmsClientInfoDO);

        // get contract products
        QueryWrapper<ImsContractProductDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ImsContractProductDO::getContractId, imsContractDO.getId());
        List<ImsContractProductDO> imsContractProductDOS = contractProductMapper.selectList(wrapper);
        result.setProducts(imsContractProductDOS);

        Set<Long> skuIds = new HashSet<>();
        Set<Long> spuIds = new HashSet<>();
        for (ImsContractProductDO contractProductDO : imsContractProductDOS) {
            skuIds.add(contractProductDO.getSkuId());
            spuIds.add(contractProductDO.getSpuId());
        }

        result.setSkus(this.skuInfoService.getSkuDtoByIds(new ArrayList<>(skuIds)));
        result.setSpus(this.spuInfoService.getSpuListByIds(new ArrayList<>(spuIds)));

        UserDO localUser = LocalUser.getLocalUser();
        result.setCurrentUser(localUser);


        return result;
    }

    @Override
    public void delete(Long id) {
        // delete contract products
        QueryWrapper<ImsContractProductDO> cpWrapper = new QueryWrapper<>();
        cpWrapper.lambda().eq(ImsContractProductDO::getContractId, id);
        contractProductMapper.delete(cpWrapper);

        // delete contract
        contractMapper.deleteById(id);
    }

    @Override
    public void updateContract(Long id, ContractDTO validator) {
        ImsContractDO imsContractDO = new ImsContractDO();
        BeanUtils.copyProperties(validator, imsContractDO);
        imsContractDO.setId(id);
        contractMapper.updateById(imsContractDO);

        // delete contract products
        QueryWrapper<ImsContractProductDO> cpWrapper = new QueryWrapper<>();
        cpWrapper.lambda().eq(ImsContractProductDO::getContractId, id);
        contractProductMapper.delete(cpWrapper);

        // add contract products
        for (ImsContractProductDO spuId : validator.getSkus()) {
            ImsContractProductDO imsContractProductDO = new ImsContractProductDO();
            BeanUtils.copyProperties(spuId, imsContractProductDO);
            imsContractProductDO.setContractId(imsContractDO.getId());
            contractProductMapper.insert(imsContractProductDO);
        }
    }
}
