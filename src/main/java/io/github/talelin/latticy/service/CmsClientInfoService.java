package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.ClientDTO;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
