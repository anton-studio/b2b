package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.ImsContractDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.LogDO;
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
 * @since 2021-03-17
 */
@Repository
public interface ImsContractMapper extends BaseMapper<ImsContractDO> {
    IPage<ImsContractDO> selectWithFilter(Page<ImsContractDO> pager, @Param("params") Map<String, Object> params);
}
