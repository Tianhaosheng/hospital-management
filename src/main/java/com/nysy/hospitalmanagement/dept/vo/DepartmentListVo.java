package com.nysy.hospitalmanagement.dept.vo;

import com.nysy.hospitalmanagement.dept.entity.DepartmentEntity;
import lombok.Data;

@Data
public class DepartmentListVo extends DepartmentEntity {

    private String departmentPrincipalName;
}
