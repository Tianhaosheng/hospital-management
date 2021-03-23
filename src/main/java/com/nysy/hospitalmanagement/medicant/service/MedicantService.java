package com.nysy.hospitalmanagement.medicant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.medicant.entity.MedicantEntity;

import java.util.Map;

/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-29 14:46:08
 */
public interface MedicantService extends IService<MedicantEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

