package com.nysy.hospitalmanagement.dept.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-11 14:59:03
 */
public interface DepartmentService extends IService<DepartmentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Long> getPrincipalPath(Long departmentPrincipal);
}

