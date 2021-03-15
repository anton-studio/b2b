package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.Required;
import io.github.talelin.latticy.dto.ClientDTO;
import io.github.talelin.latticy.model.CmsClientFilesDO;
import io.github.talelin.latticy.service.CmsClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.CmsClientInfoDO;
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
@RequestMapping("/v1/cms-client-info")
public class CmsClientInfoController {

    @Autowired
    CmsClientInfoService clientInfoService;


    @PostMapping("")
    @LoginRequired
    public CreatedVO create(@RequestBody ClientDTO validator) {
        clientInfoService.create(validator);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id,
                            @RequestBody ClientDTO validator) {
        clientInfoService.update(id, validator);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Long id) {
        clientInfoService.deleteClient(id);
        return new DeletedVO();
    }

    @GetMapping("/detail/{id}")
    public ClientDTO getDetail(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return clientInfoService.getClientWithDetail(id);
    }

    @GetMapping("/{id}")
    public CmsClientInfoDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/listSea")
    public List<CmsClientInfoDO> listSea(){
        QueryWrapper<CmsClientInfoDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CmsClientInfoDO::getOwnedBy, 0L);
        List<CmsClientInfoDO> cmsClientInfoDOS2 = clientInfoService.getBaseMapper().selectList(wrapper);
        // todo: also get ownedBy: null
        return cmsClientInfoDOS2;
    }

    @GetMapping("/list")
    public List<CmsClientInfoDO> list(){
        return clientInfoService.getBaseMapper().selectList(null);
    }

    @GetMapping("/page")
    public PageResponseVO<CmsClientInfoDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
