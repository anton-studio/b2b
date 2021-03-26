package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.ProductDTO;
import io.github.talelin.latticy.model.PmsCategoryDO;
import io.github.talelin.latticy.model.PmsSpuInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.vo.SpuInfoWithAttr;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-04
 */
public interface PmsSpuInfoService extends IService<PmsSpuInfoDO> {
    boolean createSpuIfo(PmsSpuInfoDO validator);

    void createFullProduct(ProductDTO validator);

    ProductDTO getProductDetailById(Long id);

    void updateProduct(Long spuId, ProductDTO validator);

    void deleteProduct(Long id);

    List<SpuInfoWithAttr> getSpuListByIds(List<Long> ids);
}
