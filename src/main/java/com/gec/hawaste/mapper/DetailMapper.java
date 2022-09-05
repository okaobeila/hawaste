package com.gec.hawaste.mapper;

import com.gec.hawaste.custom.DetailDo;
import com.gec.hawaste.entity.Detail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface DetailMapper extends BaseMapper<Detail> {

    @Select("select  " +
            " de.*, " +
            " wt.CODE waste_type_code, " +
            " wt.NAME waste_type_name, " +
            " wa.CODE waste_dode " +
            "from " +
            "   detail de, " +
            "   waste_type wt, " +
            "   waste wa " +
            "where  " +
            "   de.work_order_id = #{oid} " +
            "   and de.waste_type_id = wt.id " +
            "   and de.waste_id = wa.id")
    List<DetailDo> selectByOrderId(Serializable oid);
}
