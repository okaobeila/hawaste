package com.gec.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.custom.WorkOrderDetailDo;
import com.gec.hawaste.entity.WorkOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.hawaste.custom.WorkOrderDo;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {

    @Select("select  " +
            " wo.*, " +
            " cu.name cerate_user_name, " +
            " co.name create_office_name, " +
            " tu.name transport_user_name, " +
            " ru.name recipient_user_name " +
            "from  " +
            " work_order wo  " +
            " left join sys_user  cu  " +
            " on wo.create_user_id = cu.id " +
            " left join sys_office co  " +
            " on  cu.office_id = co.id " +
            " left join sys_user tu " +
            " on wo.transport_user_id = tu.id " +
            " left join sys_office `to`   " +
            " on tu.office_id = `to`.id " +
            " left join sys_user ru   " +
            " on wo.recipient_user_id = ru.id " +
            " left join sys_office ro   " +
            " on ru.office_id = ro.id ${ew.customSqlSegment}")
    IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Wrapper ew);

    @Select("select  " +
            " wo.*, " +
            " cu.name create_user_name, " +
            " cu.phone create_user_phone, " +
            " co.name create_office_name, " +
            " tu.name transport_user_name, " +
            " tu.phone transport_user_phone, " +
            " `to`.name transport_office_name, " +
            " ru.name recipient_user_name, " +
            " ru.phone recipient_user_phone, " +
            " ro.name recipient_office_name " +
            "from  " +
            " work_order wo  " +
            " left join sys_user cu   " +
            " on wo.create_user_id = cu.id " +
            " left join sys_office co  " +
            " on cu.office_id = co.id " +
            " left join sys_user tu  " +
            " on wo.transport_user_id = tu.id " +
            " left join sys_office `to` " +
            " on tu.office_id = `to`.id " +
            " left join sys_user  ru  " +
            " on wo.recipient_user_id = ru.id " +
            " left join sys_office ro " +
            " on ru.office_id = ro.id " +
            "where " +
            " wo.del_flag = 0 " +
            " and wo.id = #{oid} ")
    WorkOrderDetailDo selectDetailById(Serializable oid);
}
