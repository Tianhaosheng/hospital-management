package com.nysy.hospitalmanagement.personnel.vo;

import lombok.Data;

@Data
public class EmployeeVo {

    private Long id;
    /**
     * 员工姓名
     */
    private String employeeName;
    /**
     * 员工性别
     */
    private String employeeSex;
    /**
     * 上级领导
     */
    private Long employeeSuperiorId;
    /**
     * 员工职位
     */
    private String employeePositionName;
    /**
     * 员工描述
     */
    private String employeeDescription;
}
