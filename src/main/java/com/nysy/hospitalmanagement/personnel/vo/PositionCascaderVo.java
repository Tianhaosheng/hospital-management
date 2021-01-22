package com.nysy.hospitalmanagement.personnel.vo;

import lombok.Data;

import java.util.List;

@Data
public class PositionCascaderVo {

    private Long id;
    private String name;
    private List<EmployeeCascaderVo> children;
}
