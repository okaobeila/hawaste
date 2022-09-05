package com.gec.hawaste.custom;

import com.gec.hawaste.entity.WorkOrder;
import lombok.Data;

import java.util.List;

@Data
public class WorkOrderDetailDo extends WorkOrder {
    /**
     * 	cu.name create_user_name,
     * 	cu.phone create_user_phone,
     * 	co.name create_office_name,
     * 	tu.name transport_user_name,
     * 	tu.phone transport_user_phone,
     * 	`to`.name transport_office_name,
     * 	ru.name recipient_user_name,
     * 	ru.phone recipient_user_phone,
     * 	ro.name recipient_office_name
     */

    private List<DetailDo> details;
    private List<TransferDo> transfers;
    private String createUserName;
    private String createUserPhone;
    private String createOfficeName;
    private String transportUserName;
    private String transportUserPhone;
    private String transportOfficeName;
    private String recipientUserName;
    private String recipientUserPhone;
    private String recipientOfficeName;

}
