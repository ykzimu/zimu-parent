package com.zimu.mapper;

import com.zimu.domain.info.RoleMenuInfo;
import com.zimu.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<RoleMenuInfo> selectRoleMenus(Map<String, Object> params);

    List<RoleEntity> selectByUserId(@Param("userId") Long userId);

}
