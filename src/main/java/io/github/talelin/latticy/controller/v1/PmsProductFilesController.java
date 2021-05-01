package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.model.CmsClientFilesDO;
import io.github.talelin.latticy.service.PmsProductFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.PmsProductFilesDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

/**
* @author generator@TaleLin
* @since 2021-03-10
*/
@RestController
@RequestMapping("/v1/pms-product-files")
public class PmsProductFilesController {

    @Autowired
    PmsProductFilesService productFilesService;

    @PostMapping("")
    public CreatedVO create(@RequestBody PmsProductFilesDO validator) {
        productFilesService.create(validator);
        return new CreatedVO();
    }

    @GetMapping("bySpuId/{id}")
    public List<PmsProductFilesDO> getBySpuId(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return productFilesService.getBySpuId(id);
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id,
                            @RequestBody Map<String, String> fileNameMap) {
        PmsProductFilesDO productFilesDO = productFilesService.getBaseMapper().selectById(id);
        productFilesDO.setFileName(fileNameMap.get("fileName"));
        productFilesService.updateById(productFilesDO);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        productFilesService.getBaseMapper().deleteById(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public PmsProductFilesDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/page")
    public PageResponseVO<PmsProductFilesDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
