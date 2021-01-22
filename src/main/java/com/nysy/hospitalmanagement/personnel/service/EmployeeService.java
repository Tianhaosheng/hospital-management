package com.nysy.hospitalmanagement.personnel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-13 10:37:15
 */
public interface EmployeeService extends IService<EmployeeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EmployeeEntity> getByPosition(Long id);

    List<Long> getPrincipalPath(Long departmentPrincipal);

    List<EmployeeEntity> getListBySuperiorId(Long positionSuperiorId);
}

