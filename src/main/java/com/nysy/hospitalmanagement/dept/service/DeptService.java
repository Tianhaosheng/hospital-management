package com.nysy.hospitalmanagement.dept.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.dept.entity.DeptEntity;
import com.nysy.hospitalmanagement.dept.vo.DeptCascaderVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-11 14:59:03
 */
public interface DeptService extends IService<DeptEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DeptCascaderVo> getCascaderList();

    List<Long> getDeptCascader(Long employeeDeptId);
}

