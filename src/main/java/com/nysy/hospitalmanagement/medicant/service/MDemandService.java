package com.nysy.hospitalmanagement.medicant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.medicant.entity.MDemandEntity;

import java.util.Map;

/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-02-03 16:18:02
 */
public interface MDemandService extends IService<MDemandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

