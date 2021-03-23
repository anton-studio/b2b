package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.model.PmsCategoryDO;
import io.github.talelin.latticy.service.PmsAttrGroupService;
import io.github.talelin.latticy.service.PmsProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.PmsAttrGroupDO;
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
* @since 2021-03-04
*/
@RestController
@RequestMapping("/v1/pms-attr-group")
public class PmsAttrGroupController {

    @Autowired
    PmsAttrGroupService pmsAttrGroupService;

    @Autowired
    PmsProductAttrValueService pmsProductAttrValueService;

    @PostMapping("")
    public CreatedVO create(@RequestBody PmsAttrGroupDO validator) {
        pmsAttrGroupService.createAttrGroup(validator);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        pmsAttrGroupService.getBaseMapper().deleteById(id);
        pmsProductAttrValueService.deleteByAttrId(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public PmsAttrGroupDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/list")
    public List<PmsAttrGroupDO> list() {
        return pmsAttrGroupService.getBaseMapper().selectList(null);
    }

    @GetMapping("/page")
    public PageResponseVO<PmsAttrGroupDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
