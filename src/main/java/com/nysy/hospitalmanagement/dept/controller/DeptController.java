package com.nysy.hospitalmanagement.dept.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;
import com.nysy.hospitalmanagement.dept.service.DepartmentService;
import com.nysy.hospitalmanagement.dept.vo.DepartmentPathVo;
import com.nysy.hospitalmanagement.dept.vo.DepartmentVo;
import com.nysy.hospitalmanagement.dept.vo.DeptPathVo;
import com.nysy.hospitalmanagement.personnel.service.EmployeeService;
import com.nysy.hospitalmanagement.personnel.service.PositionService;
import com.nysy.hospitalmanagement.personnel.vo.PositionCascaderVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nysy.hospitalmanagement.dept.entity.DeptEntity;
import com.nysy.hospitalmanagement.dept.service.DeptService;
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
@RequestMapping("dept/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

//    /dept/dept/department/list

    @GetMapping("/department/list")
    @RequiresPermissions("dept:department:list")
    public R departmentList(){
        List<DepartmentEntity> departmentEntities = departmentService.list();
        List<DepartmentVo> collect = departmentEntities.stream().map((departmentEntity) -> {
            DepartmentVo departmentVo = new DepartmentVo();
            BeanUtils.copyProperties(departmentEntity, departmentVo);
            return departmentVo;
        }).collect(Collectors.toList());
        return R.ok().put("data",collect);
    }

    /**
     * 更新启用状态
     */
    @PostMapping("/update/status")
    @RequiresPermissions("dept:dept:updateStatus")
    public R updateStatus(@RequestBody DeptEntity deptEntity){
        deptService.updateById(deptEntity);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dept:dept:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deptService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("dept:dept:info")
    public R info(@PathVariable("id") Long id){
		DeptEntity dept = deptService.getById(id);

        List<Long> deptPrincipalPath =  employeeService.getPrincipalPath(dept.getDeptPrincipal());
        DeptPathVo deptPathVo = new DeptPathVo();
        BeanUtils.copyProperties(dept,deptPathVo);
        deptPathVo.setDeptPrincipalPath(deptPrincipalPath);


        List<PositionCascaderVo> employeeCascader = positionService.getEmployeeCascader();

        return R.ok().put("dept", deptPathVo).put("employeeCascader",employeeCascader);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dept:dept:save")
    public R save(@RequestBody DeptEntity dept){
		deptService.save(dept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dept:dept:update")
    public R update(@RequestBody DeptEntity dept){
		deptService.updateById(dept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dept:dept:delete")
    public R delete(@RequestBody Long[] ids){
		deptService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
