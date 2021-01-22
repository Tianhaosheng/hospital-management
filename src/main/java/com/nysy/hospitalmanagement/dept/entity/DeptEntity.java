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
@TableName("hospital_dept")
public class DeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 科室编号
	 */
	@TableId
	private Long id;
	/**
	 * 科室名字
	 */
	private String deptName;
	/**
	 * 科室负责人
	 */
	private Long deptPrincipal;
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
