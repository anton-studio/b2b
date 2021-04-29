package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.CmsClientInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.ImsContractDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    IPage<CmsClientInfoDO> selectWithFilter(Page<CmsClientInfoDO> pager, @Param("params") Map<String, Object> params);
}
