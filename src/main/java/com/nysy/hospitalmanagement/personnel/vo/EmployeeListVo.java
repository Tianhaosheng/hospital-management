package com.nysy.hospitalmanagement.personnel.vo;

import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import lombok.Data;

@Data
public class EmployeeListVo extends EmployeeEntity {

    private String employeeSuperiorName;

}
