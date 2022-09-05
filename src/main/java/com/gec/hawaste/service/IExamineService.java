package com.gec.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.entity.Examine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.hawaste.custom.ExamineDo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface IExamineService extends IService<Examine> {
    public IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, Map<String, Object> params);

}
