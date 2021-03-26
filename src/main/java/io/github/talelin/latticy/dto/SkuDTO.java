package io.github.talelin.latticy.dto;

import io.github.talelin.latticy.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SkuDTO extends PmsSkuInfoDO {
    private List<PmsSkuPriceInfoDO> priceList;
    private List<PmsSkuSaleAttrValueDO> saleAttrList;
}
