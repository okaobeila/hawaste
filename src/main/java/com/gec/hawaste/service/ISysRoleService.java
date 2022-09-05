package com.gec.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.custom.SysRoleDo;
import com.gec.hawaste.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface ISysRoleService extends IService<SysRole> {

    public IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Map<String,Object> params);

    List<SysRole> selectRoleByUid(Long uid);

    SysRoleDo selectOne(Long id);

    boolean updateById(SysRole entity);
}
