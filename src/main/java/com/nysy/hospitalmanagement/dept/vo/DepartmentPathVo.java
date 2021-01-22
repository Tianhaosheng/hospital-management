package com.nysy.hospitalmanagement.dept.vo;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentPathVo {

    private Long id;
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 部门负责人
     */
    private List<Long> departmentPrincipalPath;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Integer enable;
}
