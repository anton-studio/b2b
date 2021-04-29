package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.mapper.ImsContractMapper;
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
import java.util.Map;

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

    @Autowired
    ImsContractMapper contractMapper;

    @GetMapping("/getPrintData/{id}")
    @LoginRequired
    public PrintDataVO getDataForPrint(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return contractService.getDataForPrint(id);
    }

    @PostMapping("")
    @LoginRequired
    public CreatedVO create(@RequestBody ContractDTO validator) {
        contractService.createContract(validator);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @LoginRequired
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id, @RequestBody ContractDTO validator) {
        contractService.updateContract(id, validator);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @LoginRequired
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        contractService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    @LoginRequired
    public ContractDTO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return contractService.getContractDetail(id);
    }

    @GetMapping("/listPending")
    @LoginRequired
    public List<ImsContractDO> listPending(){
        QueryWrapper<ImsContractDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ImsContractDO::getReviewStatus, "审核中");
        return contractService.getBaseMapper().selectList(wrapper);
    }

    @GetMapping("/list")
    @LoginRequired
    public List<ImsContractDO> list(){
        Long id = LocalUser.getLocalUser().getId();
        QueryWrapper<ImsContractDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ImsContractDO::getOwnedBy, id);
        List<Long> adminIds = new ArrayList<>();
        adminIds.add(1l);
        Boolean showAll = adminIds.contains(id);
        return contractService.getBaseMapper().selectList(showAll ? null : wrapper);
    }

//    @PostMapping("/searchlist")
//    @LoginRequired
//    public PageResponseVO<ImsContractDO> searchList(@RequestParam(name = "count", required = false, defaultValue = "10")
//                                              @Min(value = 1, message = "{page.count.min}")
//                                              @Max(value = 30, message = "{page.count.max}") Long count,
//                                          @RequestParam(name = "page", required = false, defaultValue = "0")
//                                              @Min(value = 0, message = "{page.number.min}") Long page,
//                                          @RequestBody Map<String, Object> params){
//        // todo below code maybe need change
//        return contractService.getPageWithFilter(page, count, params);
//    }

    @PostMapping("/page")
    public PageResponseVO<ImsContractDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page,
            @RequestBody Map<String, Object> params
    ) {
        IPage<ImsContractDO> res = contractService.getPageWithFilter(page, count, params);
        return PageUtil.build(res);
    }

}
