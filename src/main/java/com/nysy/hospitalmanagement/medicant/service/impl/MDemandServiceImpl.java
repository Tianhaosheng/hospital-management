package com.nysy.hospitalmanagement.medicant.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.Query;

import com.nysy.hospitalmanagement.medicant.dao.MDemandDao;
import com.nysy.hospitalmanagement.medicant.entity.MDemandEntity;
import com.nysy.hospitalmanagement.medicant.service.MDemandService;


@Service("mDemandService")
public class MDemandServiceImpl extends ServiceImpl<MDemandDao, MDemandEntity> implements MDemandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MDemandEntity> page = this.page(
                new Query<MDemandEntity>().getPage(params),
                new QueryWrapper<MDemandEntity>()
        );

        return new PageUtils(page);
    }

}