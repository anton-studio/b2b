package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opencsv.CSVWriter;
import io.github.talelin.core.annotation.AdminRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.Required;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.ClientDTO;
import io.github.talelin.latticy.model.CmsClientFilesDO;
import io.github.talelin.latticy.model.ImsContractDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.service.CmsClientInfoService;
import io.github.talelin.latticy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author generator@TaleLin
* @since 2021-03-13
*/
@RestController
@RequestMapping("/v1/cms-client-info")
public class CmsClientInfoController {

    @Autowired
    CmsClientInfoService clientInfoService;

    @Autowired
    UserService userService;


    @PostMapping("")
    @LoginRequired
    public CreatedVO create(@RequestBody ClientDTO validator) {
        clientInfoService.create(validator);
        return new CreatedVO();
    }

    @PostMapping("/importClients")
    @LoginRequired
    public Map<String, List<ClientDTO>> importAll(@RequestBody List<ClientDTO> validator) {
        Map<String, List<ClientDTO>> importResult = clientInfoService.importAll(validator);
        return importResult;
    }

    @GetMapping("/checkValid/{email}")
    public Map<String, Boolean>isEmailValid(@PathVariable String email) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("isValid", clientInfoService.isEmailValid(email));
        return map;
    }

    @GetMapping("/checkValidCode/{code}")
    public Map<String, Boolean>isCodeValid(@PathVariable String code) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("isValid", clientInfoService.isCodeValid(code));
        return map;
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Long id,
                            @RequestBody ClientDTO validator) {
        clientInfoService.update(id, validator);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @AdminRequired
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
        return clientInfoService.getPublicSeaClients();
    }

    @GetMapping("/acquireClient/{id}")
    @LoginRequired
    public UpdatedVO acquireClient(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id){
        CmsClientInfoDO cmsClientInfoDO = clientInfoService.getBaseMapper().selectById(id);
        Long userId = LocalUser.getLocalUser().getId();
        cmsClientInfoDO.setOwnedBy(userId);
        clientInfoService.getBaseMapper().updateById(cmsClientInfoDO);
        return new UpdatedVO();
    }

    @GetMapping("/list")
    @LoginRequired
    public List<CmsClientInfoDO> list(){
        Long id = LocalUser.getLocalUser().getId();
        QueryWrapper<CmsClientInfoDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CmsClientInfoDO::getOwnedBy, id);
        List<Long> adminIds = new ArrayList<>();
        adminIds.add(1l);
        Boolean showAll = adminIds.contains(id);
        return clientInfoService.getBaseMapper().selectList(showAll ? null : wrapper);
    }

    @PostMapping("/page")
    @LoginRequired
    public PageResponseVO<CmsClientInfoDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page,
            @RequestBody Map<String, Object> params
    ) {
//        IPage<CmsClientInfoDO> myPage = new Page<>(page,count);
//        Long id = LocalUser.getLocalUser().getId();
//        QueryWrapper<CmsClientInfoDO> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(CmsClientInfoDO::getOwnedBy, id);
//        List<Long> adminIds = new ArrayList<>();
//        adminIds.add(1l);
//        Boolean showAll = adminIds.contains(id);
//        IPage<CmsClientInfoDO> cmsClientInfoDOIPage = clientInfoService.getBaseMapper().selectPage(myPage, showAll ? null : wrapper);
//        return PageUtil.build(myPage, cmsClientInfoDOIPage.getRecords());

        UserDO user = LocalUser.getLocalUser();
        // public sea
        if (params != null && params.get("owned_by") != null && params.get("owned_by").toString().contains("[0]")) {
            params.put("owned_by", Arrays.asList(0l));
            IPage<CmsClientInfoDO> res = clientInfoService.getPageWithFilter(page, count, params);
            return PageUtil.build(res);
        }
        if (!Arrays.asList(1l, 2l).contains(user.getId())) {
            // sales
            params.put("owned_by", Arrays.asList(user.getId()));
        } else {
            // need to get all user id, but not the 0l as this will be public sea
            List<UserDO> userDOS = userService.getBaseMapper().selectList(null);
            List<Long> allUids = new ArrayList<>();
            for (UserDO userDO : userDOS) {
                allUids.add(userDO.getId());
            }
            params.put("owned_by", allUids);
        }
        IPage<CmsClientInfoDO> res = clientInfoService.getPageWithFilter(page, count, params);
        return PageUtil.build(res);
    }

    @GetMapping("/export")
    @AdminRequired
    public void export(HttpServletResponse response) throws IOException {
        List<CmsClientInfoDO> clientList = clientInfoService.getBaseMapper().selectList(null);
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{
                        "ID",
                        "client name",
                        "Address"
                }
        ); // csv header
        for (CmsClientInfoDO item : clientList) {
            list.add(new String[]{
                            item.getId().toString(),
                            item.getClientName(),
                            item.getAddress()
                    }
            );
        }
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=client_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            writer.writeAll(list);
        }
    }

}
