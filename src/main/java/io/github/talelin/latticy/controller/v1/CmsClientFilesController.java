package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.model.CmsClientFollowLogDO;
import io.github.talelin.latticy.model.PmsProductFilesDO;
import io.github.talelin.latticy.service.CmsClientFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.CmsClientFilesDO;
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
@RequestMapping("/v1/cms-client-files")
public class CmsClientFilesController {

    @Autowired
    CmsClientFilesService clientFilesService;

    @PostMapping("")
    public CreatedVO create(@RequestBody CmsClientFilesDO validator) {
        clientFilesService.create(validator);
        return new CreatedVO();
    }

    @GetMapping("/byClientId/{id}")
    public List<CmsClientFilesDO> getByClientId(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return clientFilesService.getByClientId(id);
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
    public CmsClientFilesDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/list")
    public List<CmsClientFilesDO> list(){
        return clientFilesService.getBaseMapper().selectList(null);
    }

    @GetMapping("/page")
    public PageResponseVO<CmsClientFilesDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
