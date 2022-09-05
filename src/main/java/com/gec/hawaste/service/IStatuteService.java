package com.gec.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.entity.Statute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface IStatuteService extends IService<Statute> {

    public IPage<Statute> selectByCondition(IPage<Statute> page,Integer type);
}
