package com.gec.hawaste.mapper;

import com.gec.hawaste.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     *
     ######################查询角色已经分配的人员
     * @param rid
     * @return
     */
    @Select("select \n" +
            "\tsu.* \n" +
            "\tfrom\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_user su\n" +
            "where \n" +
            "\tsur.del_flag = 0\n" +
            "and sur.role_id = #{rid}\n" +
            "and sur.user_id = su.id")
    List<SysUser> selectByRid(Long rid);

    /**
     * 参数，pojo，@Param（"name"）
     * ###########################找出公司里面的未分配指定角色的人员
     */
    @Select("select\n" +
            "\t\t\t*\n" +
            "from sys_user\n" +
            "where \n" +
            "\t\t\toffice_id = #{oid}\n" +
            "\t\t\tand id not in (\n" +
            "select \n" +
            "\t\t\tsu.id\n" +
            "from \n" +
            "\t\t\tsys_user_role sur,\n" +
            "\t\t\tsys_user su\n" +
            "where \n" +
            "\t\t\tsur.del_flag = 0\n" +
            "\t\t\tand su.del_flag = 0\n" +
            "\t\t\tand sur.role_id = #{rid}\n" +
            "\t\t\tand sur.user_id = su.id\n" +
            "\t\t\t)")
    List<SysUser> selectNoRole(@Param("rid") Long rid,@Param("oid") Long oid);
}
