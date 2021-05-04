package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.ClientDTO;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-13
 */
public interface CmsClientInfoService extends IService<CmsClientInfoDO> {

    boolean create(ClientDTO validator);

    void update(Long id, ClientDTO validator);

    void deleteClient(Long id);

    ClientDTO getClientWithDetail(Long id);

    List<CmsClientInfoDO> getPublicSeaClients();

    IPage<CmsClientInfoDO> getPageWithFilter(Long page, Long count, Map<String, Object> params);

    Boolean isEmailValid(String email);

    Boolean isCodeValid(String code);

    Map<String, List<ClientDTO>> importAll(List<ClientDTO> validator);
}
