package com.nysy.hospitalmanagement.dept.service.impl;

import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;
import com.nysy.hospitalmanagement.dept.service.DepartmentService;
import com.nysy.hospitalmanagement.dept.vo.DepartmentListVo;
import com.nysy.hospitalmanagement.dept.vo.DeptCascaderVo;
import com.nysy.hospitalmanagement.dept.vo.DeptListVo;
import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.entity.PositionEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
import com.nysy.hospitalmanagement.personnel.service.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.Query;

import com.nysy.hospitalmanagement.dept.dao.DeptDao;
import com.nysy.hospitalmanagement.dept.entity.DeptEntity;
import com.nysy.hospitalmanagement.dept.service.DeptService;
import org.springframework.util.StringUtils;


@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptEntity> implements DeptService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<DeptEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)){
            wrapper.eq("id",key).or().like("dept_name",key).or().like("dept_principal",key);
        }
        IPage<DeptEntity> page = this.page(
                new Query<DeptEntity>().getPage(params),
                wrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<DeptEntity> list = (List<DeptEntity>) pageUtils.getList();
        List<DeptListVo> collect = list.stream().map((DeptEntity) -> {
            DeptListVo deptListVo = new DeptListVo();
            EmployeeEntity employeeEntity = employeeService.getById(DeptEntity.getDeptPrincipal());
            BeanUtils.copyProperties(DeptEntity, deptListVo);
            deptListVo.setDeptPrincipalName(employeeEntity.getEmployeeName());
            return deptListVo;
        }).collect(Collectors.toList());

        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public List<DeptCascaderVo> getCascaderList() {

        List<DepartmentEntity> list = departmentService.list();
        List<DeptCascaderVo> department = list.stream().map((departmentEntity) -> {
            QueryWrapper<DeptEntity> wrapper = new QueryWrapper<DeptEntity>().eq("department", departmentEntity.getDepartmentName());
            List<DeptCascaderVo> deptCascader = this.list(wrapper).stream().map((deptEntity) -> {
                DeptCascaderVo deptCascaderVo = new DeptCascaderVo();
                deptCascaderVo.setId(deptEntity.getId());
                deptCascaderVo.setName(deptEntity.getDeptName());
                deptCascaderVo.setDisabled(deptEntity.getEnable() == 0 ? true:false);
                return deptCascaderVo;
            }).collect(Collectors.toList());

            DeptCascaderVo deptCascaderVo = new DeptCascaderVo();
            deptCascaderVo.setId(departmentEntity.getId());
            deptCascaderVo.setName(departmentEntity.getDepartmentName());
            deptCascaderVo.setChildren(deptCascader);
            deptCascaderVo.setDisabled(departmentEntity.getEnable() == 0 ? true:false);
            return deptCascaderVo;
        }).collect(Collectors.toList());
        return department;
    }

    @Override
    public List<Long> getDeptCascader(Long employeeDeptId) {
        DeptEntity deptEntity = this.getById(employeeDeptId);
        List<Long> deptCascader = new ArrayList<>();
        DepartmentEntity departmentEntity = departmentService.getOne(new QueryWrapper<DepartmentEntity>().eq("department_name",deptEntity.getDepartment()));
        deptCascader.add(departmentEntity.getId());
        deptCascader.add(employeeDeptId);

        return deptCascader;
    }

}