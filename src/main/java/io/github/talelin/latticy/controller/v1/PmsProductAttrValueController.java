package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.service.PmsProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

/**
 * spu属性值前端控制器
 *
* @author generator@TaleLin
* @since 2021-03-04
*/
@RestController
@RequestMapping("/v1/pms-product-attr-value")
public class PmsProductAttrValueController {

    @Autowired
    PmsProductAttrValueService productAttrValueService;

    @PostMapping("")
    public CreatedVO create(@RequestBody PmsProductAttrValueDO validator) {
        productAttrValueService.createProductAttrValue(validator);
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
    public PmsProductAttrValueDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/page")
    public PageResponseVO<PmsProductAttrValueDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
