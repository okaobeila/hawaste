package com.gec.hawaste.custom;

import com.gec.hawaste.entity.SysOffice;
import com.gec.hawaste.entity.SysResource;
import com.gec.hawaste.entity.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class RoleDo extends SysRole {

    private String officeName;

    //角色授权资源列表
    private List<SysResource> resources;

    //跨公司授权列表
    private List<SysOffice> offices;
}
