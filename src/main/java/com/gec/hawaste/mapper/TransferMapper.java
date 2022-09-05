package com.gec.hawaste.mapper;

import com.gec.hawaste.custom.TransferDo;
import com.gec.hawaste.entity.Transfer;
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
public interface TransferMapper extends BaseMapper<Transfer> {
    @Select("select " +
            "  tr.*, " +
            "  su.NAME user_name, " +
            "  su.phone user_phone " +
            "from  " +
            "  transfer tr, " +
            "  sys_user su  " +
            "where  " +
            "  tr.work_order_id = #{oid} " +
            "  and tr.oprate_user_id = su.id " +
            "order by  " +
            "   tr.create_date desc")
    List<TransferDo> selectByOrderId(Serializable oid);
}
