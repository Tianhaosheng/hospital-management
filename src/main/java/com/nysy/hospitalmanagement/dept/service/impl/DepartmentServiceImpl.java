package com.nysy.hospitalmanagement.dept.service.impl;

import com.nysy.hospitalmanagement.dept.vo.DepartmentListVo;
import com.nysy.hospitalmanagement.personnel.controller.EmployeeController;
import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
import jdk.nashorn.internal.objects.annotations.Property;
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

import com.nysy.hospitalmanagement.dept.dao.DepartmentDao;
import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;
import com.nysy.hospitalmanagement.dept.service.DepartmentService;


@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, DepartmentEntity> implements DepartmentService {


    @Autowired
    private EmployeeService employeeService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<DepartmentEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)){
            wrapper.eq("id",key).or().like("department_name",key).or().like("department_principal",key);
        }

        IPage<DepartmentEntity> page = this.page(
                new Query<DepartmentEntity>().getPage(params),
                wrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<DepartmentEntity> list = (List<DepartmentEntity>) pageUtils.getList();
        List<DepartmentListVo> collect = list.stream().map((departmentEntity) -> {
            DepartmentListVo departmentListVo = new DepartmentListVo();
            EmployeeEntity employeeEntity = employeeService.getById(departmentEntity.getDepartmentPrincipal());
            BeanUtils.copyProperties(departmentEntity, departmentListVo);
            departmentListVo.setDepartmentPrincipalName(employeeEntity.getEmployeeName());
            return departmentListVo;
        }).collect(Collectors.toList());

        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public List<Long> getPrincipalPath(Long departmentPrincipal) {

        ArrayList<Long> departmentPrincipalPath = new ArrayList<>();
        Long employeePositionId = employeeService.getById(departmentPrincipal).getEmployeePositionId();
        departmentPrincipalPath.add(employeePositionId);
        departmentPrincipalPath.add(departmentPrincipal);
        return departmentPrincipalPath;
    }


}