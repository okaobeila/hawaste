package com.gec.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.custom.SysRoleDo;
import com.gec.hawaste.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select " +
            " sr.*, " +
            " so.name  office_name " +
            "from  " +
            " sys_role sr, " +
            " sys_office so ${ew.customSqlSegment}")
    IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Wrapper ew);


    /**
     * 根据用户id查角色
     */
    @Select("select\n" +
            "\tsr.*\n" +
            "from\n" +
            "\tsys_user su,\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_role sr\n" +
            "where \n" +
            "\tsu.del_flag = 0\n" +
            "\tand sur.del_flag = 0\n" +
            "\tand sr.del_flag = 0\n" +
            "\tand su.id = sur.user_id\n" +
            "\tand sr.id = sur.role_id\n" +
            "\tand su.id = #{uid} ")
    List<SysRole> selectByUid(Long uid);

    /**
     * 根据角色id查询角色信息和公司名
     * @param id
     * @return
     */
    @Select("select \n" +
            "\tsr.*,\n" +
            "\tso.name office_name\n" +
            "from \n" +
            "\tsys_role sr,\n" +
            "\tsys_office so\n" +
            "where \n" +
            "\tsr.del_flag = 0\n" +
            "\tand so.del_flag = 0\n" +
            "\tand sr.office_id = so.id\n" +
            "\tand sr.id = #{rid} ")
    SysRoleDo selectByRid(Long rid);
}
