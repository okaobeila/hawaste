package com.gec.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.entity.Examine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.hawaste.custom.ExamineDo;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface ExamineMapper extends BaseMapper<Examine> {
    //%{ew.customSqlSegment}表示动态条件拼接sql
    @Select("select " +
            "ex.*, " +
            "su.name user_name, " +
            "so.name office_name " +
            "from " +
            "examine ex, " +
            "sys_user su, " +
            "sys_office so ${ew.customSqlSegment} ")
    IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, Wrapper ew);
}
