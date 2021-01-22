package com.nysy.hospitalmanagement.personnel.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PositionListVo {

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
    private String positionSuperiorName;
    /**
     * 职位描述
     */
    private String positionDescription;
}
