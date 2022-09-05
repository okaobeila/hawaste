package com.gec.hawaste.service;

import com.gec.hawaste.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface ISysUserService extends IService<SysUser> {

    public List<SysUser> selectByRid(Long rid);

    public List<SysUser> selectNoRole(Long rid,Long oid);

    SysUser findUserByUsername(String username);
}
