package com.gec.hawaste.mapper;

import com.gec.hawaste.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     *根据用户id查所有权限  去重
     */
    @Select("select distinct\n" +
            "\tsre.*\n" +
            "from \n" +
            "\tsys_user sus,\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_role sro,\n" +
            "\tsys_role_resource srr,\n" +
            "\tsys_resource sre\n" +
            "where\n" +
            "\tsus.id = #{uid} \n" +
            "\tand sus.del_flag = 0\n" +
            "\tand sur.del_flag = 0\n" +
            "\tand sro.del_flag = 0\n" +
            "\tand srr.del_flag = 0\n" +
            "\tand sre.del_flag = 0\n" +
            "\tand sus.id = sur.user_id\n" +
            "\tand sur.role_id = sro.id\n" +
            "\tand sro.id = srr.role_id\n" +
            "\tand sre.id = srr.resource_id")
    List<SysResource> selectByUid(Long uid);


    /**
     * 根据角色id查询权限
     */
    @Select("select\n" +
            "\tsre.*\n" +
            "from\n" +
            "\tsys_role sr,\n" +
            "\tsys_role_resource srr,\n" +
            "\tsys_resource sre\n" +
            "where \n" +
            "\tsrr.role_id = #{rid} \n" +
            "\tand sr.del_flag = 0\n" +
            "\tand srr.del_flag = 0 \n" +
            "\tand sre.del_flag = 0\n" +
            "\tand sre.del_flag = 0\n" +
            "  and sr.id = srr.role_id\n" +
            "\tand srr.resource_id = sre.id")
    List<SysResource> selectByRid(Long rid);
}
