package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.ContractDTO;
import io.github.talelin.latticy.model.ImsContractDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.vo.PrintDataVO;

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
}
