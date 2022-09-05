package com.gec.hawaste.custom;

import lombok.Data;

@Data
public class DetailDo {
    /**
     * wt.CODE waste_type_code,
     * 			wt.NAME waste_type_name,
     * 			wa.CODE waste_dode
     */
    private  String wasteTypeCode;
    private  String wasteTypeName;
    private  String wasteDode;

}
