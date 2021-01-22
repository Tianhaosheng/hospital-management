package com.nysy.hospitalmanagement.personnel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nysy.hospitalmanagement.personnel.entity.PositionEntity;
import com.nysy.hospitalmanagement.personnel.service.PositionService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.R;



/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-13 10:37:15
 */
@RestController
@RequestMapping("personnel/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("personnel:position:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = positionService.queryPage(params);

        return R.ok().put("page", page);
    }

//    /personnel/position/optionsList
    @RequestMapping("/optionsList")
    public R optionsList(){
        List<PositionEntity> list = positionService.list();
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setId((long) 0);
        positionEntity.setPositionName("无");
        list.add(positionEntity);
        return R.ok().put("optionsList", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("personnel:position:info")
    public R info(@PathVariable("id") Long id){
		PositionEntity position = positionService.getById(id);

        return R.ok().put("position", position);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("personnel:position:save")
    public R save(@RequestBody PositionEntity position){
		positionService.save(position);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("personnel:position:update")
    public R update(@RequestBody PositionEntity position){
		positionService.updateById(position);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("personnel:position:delete")
    public R delete(@RequestBody Long[] ids){
		positionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
