package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.model.CmsContractDO;
import io.github.talelin.latticy.service.CmsClientFollowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.CmsClientFollowLogDO;
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
* @since 2021-03-13
*/
@RestController
@RequestMapping("/v1/cms-client-follow-log")
public class CmsClientFollowLogController {

    @Autowired
    CmsClientFollowLogService clientFollowLogService;

    @PostMapping("")
    @LoginRequired
    public CreatedVO create(@RequestBody CmsClientFollowLogDO validator) {
        clientFollowLogService.create(validator);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public CmsClientFollowLogDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/listByClientId/{id}")
    public List<CmsClientFollowLogDO> listByClientId(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id){
        QueryWrapper<CmsClientFollowLogDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CmsClientFollowLogDO::getClientId, id);
        return clientFollowLogService.getBaseMapper().selectList(wrapper);
    }

    @GetMapping("/list")
    public List<CmsClientFollowLogDO> list(){
        return clientFollowLogService.getBaseMapper().selectList(null);
    }

    @GetMapping("/page")
    public PageResponseVO<CmsClientFollowLogDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
