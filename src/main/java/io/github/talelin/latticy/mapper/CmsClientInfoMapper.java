package io.github.talelin.latticy.mapper;

import io.github.talelin.latticy.model.CmsClientInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-03-25
 */

@Repository
public interface CmsClientInfoMapper extends BaseMapper<CmsClientInfoDO> {
    List<CmsClientInfoDO> getPublicSeaClient();
}
