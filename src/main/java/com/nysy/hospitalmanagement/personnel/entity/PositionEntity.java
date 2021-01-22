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
@TableName("hospital_position")
public class PositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 职位编号
	 */
	@TableId
	private Long id;
	/**
	 * 职位名
	 */
	private String positionName;
	/**
	 * 上级
	 */
	private Long positionSuperiorId;
	/**
	 * 职位描述
	 */
	private String positionDescription;

}
