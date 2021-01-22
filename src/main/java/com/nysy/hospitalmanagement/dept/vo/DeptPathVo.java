package com.nysy.hospitalmanagement.dept.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeptPathVo {
    private Long id;
    /**
     * 部门名
     */
    private String deptName;
    /**
     * 部门负责人
     */
    private List<Long> deptPrincipalPath;
    /**
     * 科室地址
     */
    private String deptAddress;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Integer enable;
    /**
     * 所属部门
     */
    private String department;
}
