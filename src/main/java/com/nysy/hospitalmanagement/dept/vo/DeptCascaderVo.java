package com.nysy.hospitalmanagement.dept.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class DeptCascaderVo {

    private Long id;
    private String name;
    private boolean disabled;

    @TableField(exist = false)
    private List<DeptCascaderVo> children;
}
