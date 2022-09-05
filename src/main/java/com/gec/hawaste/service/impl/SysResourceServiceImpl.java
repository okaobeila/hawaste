package com.gec.hawaste.service.impl;

import com.gec.hawaste.entity.SysResource;
import com.gec.hawaste.mapper.SysResourceMapper;
import com.gec.hawaste.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public List<SysResource> selectResourceByUid(Long uid) {
        return baseMapper.selectByUid(uid);
    }
}
