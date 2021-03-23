package com.nysy.hospitalmanagement.medicant.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nysy.hospitalmanagement.medicant.entity.MedicantEntity;
import com.nysy.hospitalmanagement.medicant.service.MedicantService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.R;



/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-29 14:46:08
 */
@RestController
@RequestMapping("medicant/medicant")
public class MedicantController {
    @Autowired
    private MedicantService medicantService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("medicant:medicant:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = medicantService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("medicant:medicant:info")
    public R info(@PathVariable("id") Long id){
		MedicantEntity medicant = medicantService.getById(id);

        return R.ok().put("medicant", medicant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("medicant:medicant:save")
    public R save(@RequestBody MedicantEntity medicant){
		medicantService.save(medicant);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("medicant:medicant:update")
    public R update(@RequestBody MedicantEntity medicant){
		medicantService.updateById(medicant);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("medicant:medicant:delete")
    public R delete(@RequestBody Long[] ids){
		medicantService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
