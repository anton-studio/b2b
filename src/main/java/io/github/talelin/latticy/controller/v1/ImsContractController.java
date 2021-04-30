package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.opencsv.CSVWriter;
import io.github.talelin.core.annotation.AdminRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.mapper.ImsContractMapper;
import io.github.talelin.latticy.mapper.ImsContractProductMapper;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.service.ImsContractService;
import io.github.talelin.latticy.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.ImsContractDO;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @LoginRequired
    public PageResponseVO<ImsContractDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page,
            @RequestBody Map<String, Object> params
    ) {
        UserDO user = LocalUser.getLocalUser();
        if (!Arrays.asList(1l, 2l).contains(user.getId())) {
            // sales
            params.put("owned_by", Arrays.asList(user.getId()));
        }
        IPage<ImsContractDO> res = contractService.getPageWithFilter(page, count, params);
        return PageUtil.build(res);
    }

    @GetMapping("/export")
    @AdminRequired
    public void export(HttpServletResponse response) throws IOException {
        List<ImsContractDO> contractDOList = contractService.getBaseMapper().selectList(null);
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{
                        "ID",
                        "Contract Time",
                        "Total Amount"
                }
        ); // csv header
        for (ImsContractDO item : contractDOList) {
            list.add(new String[]{
                            item.getId().toString(),
                            item.getContractTime().toString(),
                            item.getTotalAmount().toString()
                    }
            );
        }
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=client_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            writer.writeAll(list);
        }
    }

}
