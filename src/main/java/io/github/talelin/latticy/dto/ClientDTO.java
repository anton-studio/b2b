package io.github.talelin.latticy.dto;

import io.github.talelin.latticy.model.CmsClientInfoDO;
import io.github.talelin.latticy.model.CmsClientInterestDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO extends CmsClientInfoDO {
    private Boolean isInPublicSea;
    private List<CmsClientInterestDO> interestProducts;
}
