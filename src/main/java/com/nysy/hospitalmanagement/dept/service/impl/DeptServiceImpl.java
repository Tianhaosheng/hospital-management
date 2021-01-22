package com.nysy.hospitalmanagement.dept.service.impl;

import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;
import com.nysy.hospitalmanagement.dept.vo.DepartmentListVo;
import com.nysy.hospitalmanagement.dept.vo.DeptListVo;
import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

}