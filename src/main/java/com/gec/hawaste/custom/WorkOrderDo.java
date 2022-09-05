package com.gec.hawaste.custom;

import com.gec.hawaste.entity.WorkOrder;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkOrderDo extends WorkOrder implements Serializable {
    /**
     * cu.name cerate_user_name,
     * 	co.name create_office_name,
     * 	tu.name transport_user_name,
     * 	ru.name recipient_user_name
     */

    private  String cerateUserName;
    private  String createOfficeName;
    private  String transportUserName;
    private  String recipientUserName;
}
