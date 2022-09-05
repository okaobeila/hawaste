package com.gec.hawaste.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gec.hawaste.custom.SysRoleDo;
import com.gec.hawaste.entity.*;
import com.gec.hawaste.mapper.SysOfficeMapper;
import com.gec.hawaste.mapper.SysResourceMapper;
import com.gec.hawaste.mapper.SysRoleMapper;
import com.gec.hawaste.service.ISysRoleOfficeService;
import com.gec.hawaste.service.ISysRoleResourceService;
import com.gec.hawaste.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysResourceMapper resourceMapper;

    @Autowired
    private SysOfficeMapper officeMapper;

    @Autowired
    private ISysRoleResourceService roleResourceService;

    @Autowired
    private ISysRoleOfficeService roleOfficeService;


    @Override
    public IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Map<String, Object> params) {
        QueryWrapper<SysRoleDo> query = new QueryWrapper<>();
        query.apply("sr.del_flag = 0 and sr.office_id = so.id")
                .eq(params.containsKey("dataScope") && !ObjectUtils.isEmpty(params.get("dataScope")),"sr.data_scope",params.get("dataScope"))
                .eq(params.containsKey("id") && !ObjectUtils.isEmpty(params.get("id")),"so.id",params.get("id"))
                .like(params.containsKey("name") && !ObjectUtils.isEmpty(params.get("name")),"sr.name",params.get("name"))
                .like(params.containsKey("remarks") && !ObjectUtils.isEmpty(params.get("remarks")),"sr.remarks",params.get("remarks"));

        return  baseMapper.selectByCondition(page,query);
    }

    @Override
    public List<SysRole> selectRoleByUid(Long uid) {
        return baseMapper.selectByUid(uid);
    }

    @Override
    public SysRoleDo selectOne(Long id) {
        //查询角色信息
        SysRoleDo role = baseMapper.selectByRid(id);
        //查询角色授权资源信息
        role.setResources(resourceMapper.selectByRid(id));
        //
        if ("9".equals(role.getDataScope())){
            role.setOffices(officeMapper.selectByRid(id));
        }
        return role;
    }

    /**
     * 更新role(saveAndUpdate方法会调用该更新方法，重新该方法即可)：
     * 1.更新role本表信息
     * 2.更新role对应的resources
     * 3.更新role对应的offices
     */
    @Override
    @Transactional
    public boolean updateById(SysRole entity) {

        SysRoleDo roleDo = (SysRoleDo) entity;

        //1更新role本表信息
        super.updateById(entity);

        //2更新role对应的resources
        List<SysResource> resources = roleDo.getResources();
        if (!ObjectUtils.isEmpty(resources)){
            updateRoleResources(roleDo.getId(),resources);
        }
        //3更新role对应的offices
        List<SysOffice> offices = roleDo.getOffices();
        if (!ObjectUtils.isEmpty(offices)){ // 更新
            updateRoleOffices(roleDo.getId(),offices);
        }else {
            //取消跨机构授权
            roleOfficeService.remove(new QueryWrapper<SysRoleOffice>()
                                                        .eq("role_id",roleDo.getId())) ;
        }
        return true;

    }
    /*
     * 更新中间表sys_role_resource数据
     * */
    private void updateRoleResources(Long rid,List<SysResource> resources){
        List<SysRoleResource> roleResources = new ArrayList<>();
        resources.forEach(resource->{
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(rid);
            roleResource.setResourceId(resource.getId());
            roleResources.add(roleResource);
        });
        //删除旧数据
        roleResourceService.remove(new QueryWrapper<SysRoleResource>()
                                        .eq("role_id",rid));
        //插入数据
        roleResourceService.saveBatch(roleResources);
    }

    /*
     * 更新中间表sys_role_office数据
     * */
    private void updateRoleOffices(Long rid, List<SysOffice> offices){
        List<SysRoleOffice> roleOffices = new ArrayList<>();
        offices.forEach(office->{
            SysRoleOffice roleOffice = new SysRoleOffice();
            roleOffice.setRoleId(rid);
            roleOffice.setOfficeId(office.getId());
            roleOffices.add(roleOffice);
        });
        //删除旧数据
        QueryWrapper<SysRoleOffice> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",rid);
        System.out.println(queryWrapper);
        roleOfficeService.remove(queryWrapper);
        //roleOfficeService.removeById(27);
        //插入新数据
        roleOfficeService.saveBatch(roleOffices);
    }

}
