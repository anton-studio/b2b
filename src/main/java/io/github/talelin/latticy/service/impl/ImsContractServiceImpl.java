package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.mapper.ImsContractProductMapper;
import io.github.talelin.latticy.model.ImsContractDO;
import io.github.talelin.latticy.mapper.ImsContractMapper;
import io.github.talelin.latticy.model.ImsContractProductDO;
import io.github.talelin.latticy.service.ImsContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void createContract(ContractDTO validator) {
        ImsContractDO imsContractDO = new ImsContractDO();
        BeanUtils.copyProperties(validator, imsContractDO);

        this.getBaseMapper().insert(imsContractDO);

        for (Long spuId : validator.getSpuIds()) {
            ImsContractProductDO imsContractProductDO = new ImsContractProductDO();
            imsContractProductDO.setContractId(imsContractDO.getId());
            imsContractProductDO.setSpuId(spuId);
            contractProductMapper.insert(imsContractProductDO);
        }
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
}
