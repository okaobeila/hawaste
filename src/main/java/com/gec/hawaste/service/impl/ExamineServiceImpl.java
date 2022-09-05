package com.gec.hawaste.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gec.hawaste.entity.Examine;
import com.gec.hawaste.custom.ExamineDo;
import com.gec.hawaste.mapper.ExamineMapper;
import com.gec.hawaste.service.IExamineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements IExamineService {

    //动态拼接SQL语句
    @Override
    public IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, Map<String, Object> params) {
        QueryWrapper<ExamineDo> query = new QueryWrapper<>();
        //拼接固定sql
        query.apply("ex.del_flag=0 and ex.examine_user_id = su.id and su.office_id = so.id ")
        //拼接动态sql
                   .eq(params.containsKey("officeId") && !ObjectUtils.isEmpty(params.get("officeId")),"so.id",params.get("officeId"))
                    .eq(params.containsKey("type") && !ObjectUtils.isEmpty(params.get("type")),"ex.type",params.get("type"))
                    .like(params.containsKey("name") && !ObjectUtils.isEmpty(params.get("name")),"su.name",params.get("name"));

        return baseMapper.selectByCondition(page, query);
        }


}
