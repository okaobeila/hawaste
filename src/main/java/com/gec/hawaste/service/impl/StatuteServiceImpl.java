package com.gec.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gec.hawaste.entity.Statute;
import com.gec.hawaste.mapper.StatuteMapper;
import com.gec.hawaste.service.IStatuteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class StatuteServiceImpl extends ServiceImpl<StatuteMapper, Statute> implements IStatuteService {

    @Override
    public IPage<Statute> selectByCondition(IPage<Statute> page, Integer type) {
        QueryWrapper<Statute> query = new QueryWrapper<>();

        query.select("statute.id," +
                "statute.type," +
                "statute.title," +
                "statute.pub_date," +
                "statute.stem_from," +
                "statute.del_flag")
                .eq(!ObjectUtils.isEmpty(type),"statute.type",type);

        return baseMapper.selectPage(page,query);
    }
}
