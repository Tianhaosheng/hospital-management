package com.nysy.hospitalmanagement.personnel.vo;

import com.nysy.hospitalmanagement.personnel.entity.EmployeeEntity;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeWithCascaderVo extends EmployeeEntity {

    private List<Long> employeeDeptCascader;
}
