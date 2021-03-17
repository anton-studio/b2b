package io.github.talelin.latticy.controller.v1;


import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import io.github.talelin.latticy.service.ImsContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.ImsContractDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
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

    @PostMapping("")
    public CreatedVO create(@RequestBody ContractDTO validator) {
        contractService.createContract(validator);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        contractService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public ImsContractDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
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
