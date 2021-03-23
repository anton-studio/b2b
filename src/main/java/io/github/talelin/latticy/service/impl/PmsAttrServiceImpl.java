package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.github.talelin.latticy.dto.PmsAttrDTO;
import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.mapper.PmsAttrMapper;
import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.service.PmsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.service.PmsProductAttrValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttrDO> implements PmsAttrService {

    @Autowired
    PmsAttrMapper pmsAttrMapper;

    @Autowired
    PmsProductAttrValueService productAttrValueService;

    @Override
    public boolean createAttr(PmsAttrDO validator) {
        PmsAttrDO pmsAttrDO = new PmsAttrDO();
        BeanUtils.copyProperties(validator, pmsAttrDO);
        pmsAttrMapper.insert(pmsAttrDO);

        for (String s : pmsAttrDO.getValueSelect().split(",")) {
            PmsProductAttrValueDO pmsProductAttrValueDO = new PmsProductAttrValueDO();
            pmsProductAttrValueDO.setAttrValue(s);
            pmsProductAttrValueDO.setAttrId(pmsAttrDO.getId());
            pmsProductAttrValueDO.setAttrName(pmsAttrDO.getAttrName());
            productAttrValueService.getBaseMapper().insert(pmsProductAttrValueDO);
        }

        return true;
    }

    @Override
    public List<PmsAttrDTO> findByAttrGroupId(Long attrGroupId) {
        List<PmsAttrDO> pmsAttrDOList =
                new LambdaQueryChainWrapper<>(pmsAttrMapper)
                        .eq(PmsAttrDO::getAttrGroupId, attrGroupId)
                        .list();

        List<PmsAttrDTO> pmsAttrDTOS = new ArrayList<>();
        for (PmsAttrDO pmsAttrDO : pmsAttrDOList) {
            PmsAttrDTO pmsAttrDTO = new PmsAttrDTO();
            BeanUtils.copyProperties(pmsAttrDO, pmsAttrDTO);
            List<PmsProductAttrValueDO> valueList = productAttrValueService.getByAttrId(pmsAttrDO.getId());
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < valueList.size(); i++) {
                s.append(valueList.get(i).getAttrValue());
                if (valueList.size() - 1 > i) {
                    s.append(',');
                }
            }
            pmsAttrDTO.setValueList(s.toString());
            pmsAttrDTOS.add(pmsAttrDTO);
        }

        return pmsAttrDTOS;
    }

    @Override
    public void delete(Long id) {
        productAttrValueService.deleteByAttrId(id);
        pmsAttrMapper.deleteById(id);
    }
}
