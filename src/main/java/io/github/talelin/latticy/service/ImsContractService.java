package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.model.ImsContractDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.vo.PrintDataVO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-17
 */
public interface ImsContractService extends IService<ImsContractDO> {

    void createContract(ContractDTO validator);

    void delete(Long id);

    void updateContract(Long id, ContractDTO validator);

    ContractDTO getContractDetail(Long id);

    PrintDataVO getDataForPrint(Long id);

    IPage<ImsContractDO> getPageWithFilter(Long page, Long count, Map<String, Object> params);
}
