package com.nysy.hospitalmanagement.medicant.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nysy.hospitalmanagement.medicant.entity.MDemandEntity;
import com.nysy.hospitalmanagement.medicant.service.MDemandService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.R;



/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-02-03 16:18:02
 */
@RestController
@RequestMapping("medicant/mdemand")
public class MDemandController {
    @Autowired
    private MDemandService mDemandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("medicant:mdemand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mDemandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hospitalMDemandId}")
    //@RequiresPermissions("medicant:mdemand:info")
    public R info(@PathVariable("hospitalMDemandId") Integer hospitalMDemandId){
		MDemandEntity mDemand = mDemandService.getById(hospitalMDemandId);

        return R.ok().put("mDemand", mDemand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("medicant:mdemand:save")
    public R save(@RequestBody MDemandEntity mDemand){
		mDemandService.save(mDemand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("medicant:mdemand:update")
    public R update(@RequestBody MDemandEntity mDemand){
		mDemandService.updateById(mDemand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("medicant:mdemand:delete")
    public R delete(@RequestBody Integer[] hospitalMDemandIds){
		mDemandService.removeByIds(Arrays.asList(hospitalMDemandIds));

        return R.ok();
    }

}
