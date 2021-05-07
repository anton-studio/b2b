package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.ChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opencsv.CSVWriter;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.autoconfigure.exception.ParameterException;
import io.github.talelin.core.annotation.AdminRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.model.BookDO;
import io.github.talelin.latticy.model.ImsContractDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.service.ImsContractService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/v1/todo")
public class TodoController {

    @Autowired
    ImsContractService contractService;

    @PostMapping("")
    @LoginRequired
    public CreatedVO create(@RequestBody Map<String, Object> validator) {
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @LoginRequired
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id, @RequestBody Map<String, Object> validator) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @LoginRequired
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    @LoginRequired
    public Map<String, Object> get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return this.getDetail();
    }

    @PostMapping("/page")
    @LoginRequired
    public PageResponseVO<Map<String, Object>> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page,
            @RequestBody Map<String, Object> params
    ) {
        IPage<Map<String, Object>> res = new Page();
        res.setPages(1);
        res.setCurrent(1);
        res.setTotal(1);
        res.setSize(1);

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(this.getDetail());

        res.setRecords(resultList);
        return PageUtil.build(res);
    }

    private Map<String, Object> getDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1l);
        map.put("owned_by", 1l);
        map.put("title", "第一个 Todo");
        map.put("expect_time", new Date());
        map.put("detail", "写完这个应用");
        map.put("todo_tag", "进行中");
        map.put("todo_cate", Arrays.asList("1", "2"));
        map.put("create_time", new Date());
        map.put("update_time", new Date());
        map.put("delete_time", null);
        return map;
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
