package com.nysy.hospitalmanagement.medicant.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author tianhaosheng
 * @email 
 * @date 2021-01-29 14:46:08
 */
@Data
@TableName("hospital_medicant")
public class MedicantEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId
	private Long id;
	/**
	 * 药物类别
	 */
	private String medicantCategory;
	/**
	 * 药物名称
	 */
	private String medicantName;
	/**
	 * OTC标志
	 */
	private String medicantOtc;
	/**
	 * 药物规格
	 */
	private String medicantSpecification;
	/**
	 * 有效期
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date medicantValidity;
	/**
	 * 厂家
	 */
	private String medicantManufacturers;
	/**
	 * 库存
	 */
	private String medicantInventory;
	/**
	 * 价格
	 */
	private BigDecimal medicantPrice;

}
