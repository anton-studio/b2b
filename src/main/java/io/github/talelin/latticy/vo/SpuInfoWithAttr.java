package io.github.talelin.latticy.vo;

import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.model.PmsSpuInfoDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SpuInfoWithAttr extends PmsSpuInfoDO {
    private List<PmsProductAttrValueDO> basicAttr;
}
