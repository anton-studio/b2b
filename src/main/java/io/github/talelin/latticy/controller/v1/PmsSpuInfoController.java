package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.dto.ProductDTO;
import io.github.talelin.latticy.service.PmsSpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.PmsSpuInfoDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;

/**
* @author generator@TaleLin
* @since 2021-03-04
*/
@RestController
@RequestMapping("/v1/pms-spu-info")
@PermissionModule(value = "产品管理")
public class PmsSpuInfoController {

    @Autowired
    PmsSpuInfoService pmsSpuInfoService;

    @PostMapping("/product")
    @PermissionMeta(value = "新增产品")
    @GroupRequired
    public CreatedVO createProduct(@RequestBody ProductDTO validator) {
        pmsSpuInfoService.createFullProduct(validator);
        return new CreatedVO();
    }

    // this endpoint is for printing
    @GetMapping("detailForPrint")
    public List<PmsSpuInfoDO> getProductDetailForPrint(@RequestParam(value = "spus") String spus) {
        String[] split = spus.split(",");
        List<PmsSpuInfoDO> pmsSpuInfoDOS = pmsSpuInfoService.getBaseMapper().selectBatchIds(Arrays.asList(split));
        return pmsSpuInfoDOS;
    }

    @GetMapping("detail/{id}")
    public ProductDTO getProductDetail(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return pmsSpuInfoService.getProductDetailById(id);
    }


    @PostMapping("")
    public CreatedVO create(@RequestBody PmsSpuInfoDO validator) {
        pmsSpuInfoService.createSpuIfo(validator);
        return new CreatedVO();
    }

    @PutMapping("/updateDetail/{id}")
    @PermissionMeta(value = "更新产品")
    @GroupRequired
    public UpdatedVO updateDetail(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id,
                                  @RequestBody ProductDTO validator) {
        pmsSpuInfoService.updateProduct(id, validator);
        return new UpdatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除产品", mount = false)
    @GroupRequired
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        pmsSpuInfoService.deleteProduct(id);
        return new DeletedVO();
    }

    @GetMapping("/list")
    public List<PmsSpuInfoDO> list(){
        return pmsSpuInfoService.getBaseMapper().selectList(null);
    }

    @GetMapping("/{id}")
    public PmsSpuInfoDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/page")
    public PageResponseVO<PmsSpuInfoDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
