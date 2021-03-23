package com.nysy.hospitalmanagement.medicant.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author tianhaosheng
 * @email 
 * @date 2021-02-03 16:18:02
 */
@Data
@TableName("hospital_m_demand")
public class MDemandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer hospitalMDemandId;
	/**
	 * 药品名称
	 */
	private String hospitalMDemandName;
	/**
	 * 数目
	 */
	private String hospitalMDemandCount;
	/**
	 * 进药时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date hospitalMDemandBuytime;
	/**
	 * 厂家
	 */
	private String hospitalMDemandManufacturers;
	/**
	 * 类别
	 */
	private String hospitalMDemandCategory;
	/**
	 * 生产日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date hospitalMDemandProducedtime;
	/**
	 * 药物规格
	 */
	private String hospitalMDemandSpecification;
	/**
	 * 单价
	 */
	private BigDecimal hospitalMDemandUnitprice;
	/**
	 * 总价
	 */
	private BigDecimal hospitalMDemandAllprice;
	/**
	 * 储存温度
	 */
	private String hospitalMDemandT;
	/**
	 * 负责人
	 */
	private String hospitalMDemandStatus;
	/**
	 * 进药方式
	 */
	private String hospitalMDemandBuymay;

}
