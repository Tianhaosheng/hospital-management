package com.nysy.hospitalmanagement.personnel.entity;

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
 * @date 2021-01-13 10:37:15
 */
@Data
@TableName("hospital_employee")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 员工编号
	 */
	@TableId
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
	private Long employeePositionId;
	/**
	 * 员工描述
	 */
	private String employeeDescription;
	/**
	 * 员工照片
	 */
	private String employeePhoto;

}
