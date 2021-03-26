package io.github.talelin.latticy.dto;

import io.github.talelin.latticy.model.ImsContractDO;
import io.github.talelin.latticy.model.ImsContractProductDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContractDTO extends ImsContractDO {
    private List<ImsContractProductDO> skus;
}
