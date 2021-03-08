package io.github.talelin.latticy.dto;

import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import io.github.talelin.latticy.model.PmsSpuInfoDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO extends PmsSpuInfoDO {
    private List<PmsProductAttrValueDO> basicAttrList;
    private List<SkuDTO> skuList;
}
