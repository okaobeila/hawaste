package com.gec.hawaste.service;

import com.gec.hawaste.entity.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface ISysResourceService extends IService<SysResource> {

    List<SysResource> selectResourceByUid(Long uid);

}
