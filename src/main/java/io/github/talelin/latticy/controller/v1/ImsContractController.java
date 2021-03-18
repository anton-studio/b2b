package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.mapper.ImsContractProductMapper;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import io.github.talelin.latticy.model.ImsContractProductDO;
import io.github.talelin.latticy.service.ImsContractService;
import io.github.talelin.latticy.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.ImsContractDO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

/**
* @author generator@TaleLin
* @since 2021-03-17
*/
@RestController
@RequestMapping("/v1/ims-contract")
public class ImsContractController {

    @Autowired
    ImsContractService contractService;

    @Autowired
    ImsContractProductMapper contractProductMapper;

    @PostMapping("")
    public CreatedVO create(@RequestBody ContractDTO validator) {
        contractService.createContract(validator);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id, @RequestBody ContractDTO validator) {
        contractService.updateContract(id, validator);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        contractService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public ContractDetailVO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        ImsContractDO imsContractDO = contractService.getBaseMapper().selectById(id);
        QueryWrapper<ImsContractProductDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ImsContractProductDO::getContractId, id);
        List<ImsContractProductDO> imsContractProductDOS = contractProductMapper.selectList(wrapper);
        List<Long> spuIds = new ArrayList<>();
        for (ImsContractProductDO imsContractProductDO : imsContractProductDOS) {
            spuIds.add(imsContractProductDO.getSpuId());
        }
        ContractDetailVO contractDetailVO = new ContractDetailVO();
        BeanUtils.copyProperties(imsContractDO, contractDetailVO);
        contractDetailVO.setSpuIds(spuIds);
        return contractDetailVO;
    }

    @GetMapping("/list")
    public List<ImsContractDO> list(){
        return contractService.getBaseMapper().selectList(null);
    }

    @GetMapping("/page")
    public PageResponseVO<ImsContractDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
