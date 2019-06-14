package com.tensquare.base.entity;

import constants.ModelConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ModelConstants.TABLE_LABEL)
public class Label {

    @Id
    private String id;

    /**
     * 标签名称
     */
    private String labelname;

    /**
     * 标签状态, 0：无效  1：有效
     */
    private String state;

    /**
     * 使用数量
     */
    private Long count;

    /**
     * 是否推荐, 0：不推荐  1：推荐
     */
    private String recommend;

    /**
     * 关注数
     */
    private Long fans;
}
