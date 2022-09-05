package com.gec.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gec.hawaste.entity.SysUserRole;
import com.gec.hawaste.mapper.SysUserRoleMapper;
import com.gec.hawaste.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public int deleteBatch(Long rid, Long[] ids) {

        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.eq("role_id" ,rid).in("user_id",ids);

        return baseMapper.delete(query);
    }

    @Override
    public Boolean inserBatch(Long rid, List<Long> ids) {
        ArrayList<SysUserRole> sysUserRoles = new ArrayList<>();
        ids.forEach(aLong -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(rid);
            userRole.setUserId(aLong);
            sysUserRoles.add(userRole);
        });
        return saveBatch(sysUserRoles);
    }
}
