package com.nysy.hospitalmanagement.personnel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nysy.hospitalmanagement.personnel.entity.PositionEntity;
import com.nysy.hospitalmanagement.personnel.service.PositionService;
import com.nysy.hospitalmanagement.personnel.vo.PositionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
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
@RequestMapping("personnel/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

//      获取上级职位员工列表
//    /personnel/employee/employeeSuperiorByEmployeeId/list
    @RequestMapping("/{type}/list/{id}")
    //@RequiresPermissions("personnel:employee:list")
    public R employeeSuperiorList(@PathVariable("id") Long id,@PathVariable("type") String type){
        Long positionSuperiorId = null;
        if(type.equals("employeeSuperiorByEmployeeId")){
            positionSuperiorId = positionService.getById(employeeService.getById(id).getEmployeePositionId()).getPositionSuperiorId();
        }else {
            positionSuperiorId = id;
        }
        if(positionSuperiorId == null || positionSuperiorId == 0){
            return R.error("该职位未设置上级");
        }
        List<EmployeeEntity> employeeEntities = null;
        if(positionSuperiorId != null){
            employeeEntities = employeeService.getListBySuperiorId(positionSuperiorId);
        }

        return R.ok().put("data", employeeEntities);
    }

//    /personnel/employee/employeeSuperiorByPositionId/list
//    @RequestMapping("/employeeSuperiorByPositionId/list/{employeePositionId}")
//    //@RequiresPermissions("personnel:employee:list")
//    public R employeeSuperiorByPositionIdList(@PathVariable("employeePositionId") Long employeePositionId){
//        Long positionSuperiorId = positionService.getById(employeePositionId).getPositionSuperiorId();
//        if(positionSuperiorId == null || positionSuperiorId == 0){
//            return R.error("该职位未设置上级");
//        }
//        List<EmployeeEntity> employeeEntities = null;
//        if(positionSuperiorId != null){
//            employeeEntities = employeeService.getListBySuperiorId(positionSuperiorId);
//        }
//
//        return R.ok().put("data", employeeEntities);
//    }

//    /personnel/employee/position/list
    @RequestMapping("/position/list")
    //@RequiresPermissions("personnel:employee:list")
    public R positionList(){
        List<PositionEntity> positionEntities = positionService.list();
        List<PositionVo> collect = positionEntities.stream().map((positionEntity) -> {
            PositionVo positionVo = new PositionVo();
            BeanUtils.copyProperties(positionEntity, positionVo);
            return positionVo;
        }).collect(Collectors.toList());
        return R.ok().put("data", collect);
    }

//    /personnel/employee/getPositionName
    @PostMapping("/getPositionName")
    //@RequiresPermissions("personnel:employee:list")
    public R getPositionName(@RequestBody List<EmployeeEntity> employees) {
        System.out.println(employees);
        List<Long> collect = employees.stream().map((employeeEntity) -> {
            Long employeePositionId = employeeEntity.getEmployeePositionId();
            return employeePositionId;
        }).collect(Collectors.toList());
        List<String> positionNames = positionService.getPositionNames(collect);

        return R.ok().put("positionNames", positionNames);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("personnel:employee:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = employeeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("personnel:employee:info")
    public R info(@PathVariable("id") Long id){
		EmployeeEntity employee = employeeService.getById(id);
//		employee.setEmployeePositionId(positionService.getById(employee.getEmployeePositionId()).getId());

        return R.ok().put("employee", employee);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("personnel:employee:save")
    public R save(@RequestBody EmployeeEntity employee){
		employeeService.save(employee);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("personnel:employee:update")
    public R update(@RequestBody EmployeeEntity employee){
		employeeService.updateById(employee);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("personnel:employee:delete")
    public R delete(@RequestBody Long[] ids){
		employeeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
