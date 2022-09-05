package com.gec.hawaste.custom;

import com.gec.hawaste.entity.Examine;
import lombok.Data;

import java.io.Serializable;
/**
 *  Examine类的扩展类，Domain Object
 * */
@Data
public class ExamineDo extends Examine implements Serializable {
    private String userName;
    private String officeName;
}
