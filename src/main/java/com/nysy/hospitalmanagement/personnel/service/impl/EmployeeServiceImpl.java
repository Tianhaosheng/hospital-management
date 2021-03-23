package com.nysy.hospitalmanagement.personnel.service.impl;

import com.nysy.hospitalmanagement.dept.service.DeptService;
import com.nysy.hospitalmanagement.personnel.entity.PositionEntity;
import com.nysy.hospitalmanagement.personnel.service.PositionService;
import com.nysy.hospitalmanagement.personnel.vo.EmployeeListVo;
import com.nysy.hospitalmanagement.personnel.vo.EmployeeVo;
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

import com.nysy.hospitalmanagement.personnel.dao.EmployeeDao;
import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;


@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, EmployeeEntity> implements EmployeeService {

    @Autowired
    private PositionService positionService;
    @Autowired
    private DeptService deptService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<EmployeeEntity> queryWrapper = new QueryWrapper<>();
        String id = (String) params.get("id");
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("id",key).or().like("employee_name",key);
        }else if(id != null){
            queryWrapper.eq("id",id);
        }


        IPage<EmployeeEntity> page = this.page(
                new Query<EmployeeEntity>().getPage(params),
                queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);

        List<EmployeeEntity> employeeEntityList = (List<EmployeeEntity>) pageUtils.getList();
        List<EmployeeListVo> collect = employeeEntityList.stream().map((employeeEntity) -> {
            EmployeeListVo employeeListVo = new EmployeeListVo();
            BeanUtils.copyProperties(employeeEntity, employeeListVo);
            String employeeSuperiorName = null;
            if (employeeEntity.getEmployeeSuperiorId() != null) {
                employeeSuperiorName = this.getById(employeeEntity.getEmployeeSuperiorId()).getEmployeeName();
            }
            Long employeeDeptId = employeeEntity.getEmployeeDeptId();
            String deptName = null;
            if(employeeDeptId != null){
                deptName = deptService.getById(employeeDeptId).getDeptName();
            }

            employeeListVo.setEmployeeDeptName(deptName);
            employeeListVo.setEmployeeSuperiorName(employeeSuperiorName);
            return employeeListVo;
        }).collect(Collectors.toList());
        pageUtils.setList(collect);


        return pageUtils;
    }

    @Override
    public List<EmployeeEntity> getByPosition(Long id) {

        List<EmployeeEntity> employeeEntities = this.list(new QueryWrapper<EmployeeEntity>().eq("employee_position_id", id));
        return employeeEntities;
    }

    @Override
    public List<Long> getPrincipalPath(Long departmentPrincipal) {

        ArrayList<Long> departmentPrincipalPath = new ArrayList<>();
        Long employeePositionId = this.getById(departmentPrincipal).getEmployeePositionId();
        departmentPrincipalPath.add(employeePositionId);
        departmentPrincipalPath.add(departmentPrincipal);
        return departmentPrincipalPath;
    }

    @Override
    public List<EmployeeEntity> getListBySuperiorId(Long positionSuperiorId) {
        List<EmployeeEntity> byPosition = this.getByPosition(positionSuperiorId);
        return byPosition;
    }

}