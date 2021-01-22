package com.nysy.hospitalmanagement.dept.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.nysy.hospitalmanagement.dept.vo.DepartmentPathVo;
import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
import com.nysy.hospitalmanagement.personnel.service.PositionService;
import com.nysy.hospitalmanagement.personnel.vo.EmployeeCascaderVo;
import com.nysy.hospitalmanagement.personnel.vo.PositionCascaderVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;
import com.nysy.hospitalmanagement.dept.service.DepartmentService;
import com.nysy.hospitalmanagement.common.utils.PageUtils;
import com.nysy.hospitalmanagement.common.utils.R;



/**
 * 
 *
 * @author tianhaosheng
 * @email 
 * @date 2021-01-11 14:59:03
 */
@RestController
@RequestMapping("dept/department")
public class DepartmentController {

    @Autowired//自动注入
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

//    /dept/department/update/status
    /**
     * 更新启用状态
     */
    @PostMapping("/update/status")
    @RequiresPermissions("dept:department:updateStatus")
    public R updateStatus(@RequestBody DepartmentEntity departmentEntity){
        departmentService.updateById(departmentEntity);
        return R.ok();
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dept:department:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = departmentService.queryPage(params);


        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("dept:department:info")
    public R info(@PathVariable("id") Long id){
		DepartmentEntity department = departmentService.getById(id);
        List<Long> departmentPrincipalPath =  employeeService.getPrincipalPath(department.getDepartmentPrincipal());
        DepartmentPathVo departmentPathVo = new DepartmentPathVo();
        BeanUtils.copyProperties(department,departmentPathVo);
        departmentPathVo.setDepartmentPrincipalPath(departmentPrincipalPath);


        List<PositionCascaderVo> employeeCascader = positionService.getEmployeeCascader();

        return R.ok().put("department", departmentPathVo).put("employeeCascader",employeeCascader);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dept:department:save")
    public R save(@RequestBody DepartmentEntity department){
		departmentService.save(department);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dept:department:update")
    public R update(@RequestBody DepartmentEntity department){
		departmentService.updateById(department);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dept:department:delete")
    public R delete(@RequestBody Long[] ids){
		departmentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
