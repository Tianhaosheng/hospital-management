package com.nysy.hospitalmanagement.personnel.service.impl;

import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
import com.nysy.hospitalmanagement.personnel.vo.EmployeeCascaderVo;
import com.nysy.hospitalmanagement.personnel.vo.PositionCascaderVo;
import com.nysy.hospitalmanagement.personnel.vo.PositionListVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.Query;

import com.nysy.hospitalmanagement.personnel.dao.PositionDao;
import com.nysy.hospitalmanagement.personnel.entity.PositionEntity;
import com.nysy.hospitalmanagement.personnel.service.PositionService;


@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionDao, PositionEntity> implements PositionService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String id = (String) params.get("id");
        QueryWrapper<PositionEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("id",key).or().like("position_name",key);
        }else if(id != null){
            queryWrapper.eq("id",id);
        }
        IPage<PositionEntity> page = this.page(
                new Query<PositionEntity>().getPage(params),
                queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);

        List<PositionEntity> positionEntityList = (List<PositionEntity>) pageUtils.getList();
        List<PositionListVo> collect = positionEntityList.stream().map((positionEntity) -> {
            PositionListVo positionListVo = new PositionListVo();
            BeanUtils.copyProperties(positionEntity, positionListVo);
            String positionSuperiorName = null;
            if(positionEntity.getPositionSuperiorId()!=null){
                if(positionEntity.getPositionSuperiorId() == 0) {
                    positionSuperiorName = "无";
                }else {
                    positionSuperiorName = this.getById(positionEntity.getPositionSuperiorId()).getPositionName();
                }
            }
            positionListVo.setPositionSuperiorName(positionSuperiorName);

            return positionListVo;
        }).collect(Collectors.toList());

        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public List<String> getPositionNames(List<Long> collect) {

        List<String> collect1 = collect.stream().map((id) -> {
            PositionEntity positionEntity = this.getById(id);
            return positionEntity.getPositionName();
        }).collect(Collectors.toList());

        return collect1;

    }

    @Override
    public List<PositionCascaderVo> getEmployeeCascader() {

        List<PositionEntity> positionEntities = this.list();
        List<PositionCascaderVo> employeeCascader = positionEntities.stream().map((positionEntity) -> {
            PositionCascaderVo positionCascaderVo = new PositionCascaderVo();
            positionCascaderVo.setId(positionEntity.getId());
            positionCascaderVo.setName(positionEntity.getPositionName());
            List<EmployeeEntity> employeeEntities = employeeService.getByPosition(positionEntity.getId());
            List<EmployeeCascaderVo> children = employeeEntities.stream().map((employeeEntity) -> {
                EmployeeCascaderVo employeeCascaderVo = new EmployeeCascaderVo();
                employeeCascaderVo.setId(employeeEntity.getId());
                employeeCascaderVo.setName(employeeEntity.getEmployeeName());
                return employeeCascaderVo;
            }).collect(Collectors.toList());
            positionCascaderVo.setChildren(children);
            return positionCascaderVo;
        }).collect(Collectors.toList());

        return employeeCascader;
    }

}