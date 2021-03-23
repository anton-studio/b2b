package io.github.talelin.latticy.dto;

import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.model.PmsProductAttrValueDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PmsAttrDTO extends PmsAttrDO {
    private String valueList;
}
