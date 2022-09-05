package com.gec.hawaste.mapper;

import com.gec.hawaste.entity.SysOffice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 机构表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysOfficeMapper extends BaseMapper<SysOffice> {

    /**
     * 根据角色id查询已授权公司
     */
    @Select("select\n" +
            "\tsof.id,sof.parent_id,sof.name,sof.icon\n" +
            "from\n" +
            "\tsys_role sr,\n" +
            "\tsys_role_office sro,\n" +
            "\tsys_office sof\n" +
            "where\n" +
            "\tsro.role_id = #{rid} \n" +
            "\tand sr.del_flag = 0\n" +
            "\tand sro.del_flag = 0\n" +
            "\tand sof.del_flag = 0\n" +
            "\tand sr.id = sro.role_id\n" +
            "\tand sro.office_id = sof.id")
    List<SysOffice> selectByRid(Long rid);

}
