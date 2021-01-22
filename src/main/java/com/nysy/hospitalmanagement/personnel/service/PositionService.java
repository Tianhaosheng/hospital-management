package com.nysy.hospitalmanagement.personnel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.personnel.entity.PositionEntity;
import com.nysy.hospitalmanagement.personnel.vo.PositionCascaderVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-13 10:37:15
 */
public interface PositionService extends IService<PositionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<String> getPositionNames(List<Long> collect);

    List<PositionCascaderVo> getEmployeeCascader();
}

