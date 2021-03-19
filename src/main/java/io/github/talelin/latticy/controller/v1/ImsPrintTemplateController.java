package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.service.ImsPrintTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.ImsPrintTemplateDO;
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
* @since 2021-03-19
*/
@RestController
@RequestMapping("/v1/ims-print-template")
public class ImsPrintTemplateController {

    @Autowired
    ImsPrintTemplateService printTemplateService;

    @PostMapping("")
    public CreatedVO create(@RequestBody ImsPrintTemplateDO validator) {
        ImsPrintTemplateDO imsPrintTemplateDO = new ImsPrintTemplateDO();
        BeanUtils.copyProperties(validator, imsPrintTemplateDO);
        printTemplateService.getBaseMapper().insert(imsPrintTemplateDO);
        return new CreatedVO();
    }

    @GetMapping("/list")
    public List<ImsPrintTemplateDO> list() {
        return printTemplateService.getBaseMapper().selectList(null);
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id,
                            @RequestBody ImsPrintTemplateDO validator
    ) {
        validator.setId(id);
        printTemplateService.getBaseMapper().updateById(validator);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public ImsPrintTemplateDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return printTemplateService.getBaseMapper().selectById(id);
    }

    @GetMapping("/page")
    public PageResponseVO<ImsPrintTemplateDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
