package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.dto.PmsAttrDTO;
import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.service.PmsAttrService;
import io.github.talelin.latticy.service.PmsProductAttrValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 商品属性前端控制器
 *
* @author generator@TaleLin
* @since 2021-03-04
*/
@RestController
@RequestMapping("/v1/pms-attr")
public class PmsAttrController {

    @Autowired
    PmsAttrService pmsAttrService;

    @Autowired
    PmsProductAttrValueService productAttrValueService;

    @PostMapping("")
    public CreatedVO create(@RequestBody PmsAttrDO pmsAttrDO) {
        pmsAttrService.createAttr(pmsAttrDO);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id, @RequestBody PmsAttrDO pmsAttrDO) {
        pmsAttrDO.setId(id);
        pmsAttrService.updateById(pmsAttrDO);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        pmsAttrService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/groupId/{id}")
    public List<PmsAttrDTO> getByGroupId(@PathVariable(value = "id") Long groupId) {
        return pmsAttrService.findByAttrGroupId(groupId);
    }

    @GetMapping("/{id}")
    public PmsAttrDTO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        PmsAttrDO pmsAttrDO = pmsAttrService.getBaseMapper().selectById(id);
        List<PmsProductAttrValueDO> attrValueList = productAttrValueService.getByAttrId(id);
        PmsAttrDTO pmsAttrDTO = new PmsAttrDTO();
        BeanUtils.copyProperties(pmsAttrDO, pmsAttrDTO);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < attrValueList.size(); i++) {
            s.append(attrValueList.get(i).getAttrValue());
            if (attrValueList.size() - 1 > i) {
                s.append(',');
            }
        }
        pmsAttrDTO.setValueList(s.toString());
//        pmsAttrDTO.setValueList(attrValueList);
        return pmsAttrDTO;
    }

    @GetMapping("/page")
    public PageResponseVO<PmsAttrDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
