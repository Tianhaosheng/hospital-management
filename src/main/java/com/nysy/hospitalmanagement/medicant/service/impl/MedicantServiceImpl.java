package com.nysy.hospitalmanagement.medicant.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.Query;

import com.nysy.hospitalmanagement.medicant.dao.MedicantDao;
import com.nysy.hospitalmanagement.medicant.entity.MedicantEntity;
import com.nysy.hospitalmanagement.medicant.service.MedicantService;


@Service("medicantService")
public class MedicantServiceImpl extends ServiceImpl<MedicantDao, MedicantEntity> implements MedicantService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MedicantEntity> page = this.page(
                new Query<MedicantEntity>().getPage(params),
                new QueryWrapper<MedicantEntity>()
        );

        return new PageUtils(page);
    }

}