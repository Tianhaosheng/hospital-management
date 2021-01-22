package com.nysy.hospitalmanagement.dept.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author tianhaosheng
 * @email 
 * @date 2021-01-11 14:59:03
 */
@Data
@TableName("hospital_department")
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门id
	 */
	@TableId
	private Long id;
	/**
	 * 部门名
	 */
	private String departmentName;
	/**
	 * 部门负责人
	 */
	private Long departmentPrincipal;
	/**
	 * 启用状态[0 - 禁用，1 - 启用]
	 */
	private Integer enable;

}
