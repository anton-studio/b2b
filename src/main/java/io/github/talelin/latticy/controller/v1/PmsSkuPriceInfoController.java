package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.latticy.model.PmsSkuInfoDO;
import io.github.talelin.latticy.service.PmsSkuPriceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.PmsSkuPriceInfoDO;
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
@RequestMapping("/v1/pms-sku-price-info")
public class PmsSkuPriceInfoController {

    @Autowired
    PmsSkuPriceInfoService skuPriceInfoService;

    @PostMapping("")
    public CreatedVO create(@RequestBody PmsSkuPriceInfoDO validator) {
        skuPriceInfoService.createSkuPrice(validator);
        return new CreatedVO();
    }

    @GetMapping("/getBySku/{id}")
    public List<PmsSkuPriceInfoDO> getBySku(@PathVariable @Positive(message = "{id.positive}") Long id) {
        QueryWrapper<PmsSkuPriceInfoDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsSkuPriceInfoDO::getSkuId, id);
        return skuPriceInfoService.getBaseMapper().selectList(wrapper);
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new DeletedVO();
    }

    @GetMapping("/skuId/{id}")
    public List<PmsSkuPriceInfoDO> getSkuPriceInfoBySku(@PathVariable Long id) {
        return skuPriceInfoService.findBySkuId(id);
    }

    @GetMapping("/{id}")
    public PmsSkuPriceInfoDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/page")
    public PageResponseVO<PmsSkuPriceInfoDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
