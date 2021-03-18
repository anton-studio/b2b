package io.github.talelin.latticy.vo;

import io.github.talelin.latticy.model.ImsContractDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContractDetailVO extends ImsContractDO {
    private List<Long> spuIds;
}
