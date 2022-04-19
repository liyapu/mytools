package com.lyp.learn.base.jdbc.po;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表名: grocery_refund
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroceryRefundPO {

    /**
     * 字段: id
     * 说明: 自增主键
     */
    private Long id;

    /**
     * 字段: sequence
     */
    private String sequence;

    /**
     * 字段: supplier_id
     * 说明: 供应商id
     */
    private Long supplierId;

    /**
     * 字段: poi_id
     * 说明: 门店id
     */
    private Long poiId;

    /**
     * 字段: poi_name
     * 说明: 门店名称
     */
    private String poiName;

    /**
     * 字段: poi_type
     * 说明: 门店类型
     */
    private Byte poiType;

    /**
     * 字段: return_date
     * 说明: 预计退货日期
     */
    private Long returnDate;

    /**
     * 字段: status
     * 说明: 状态 0:草稿 1:待退货 2:已完成 3:已关闭 4:已取消5:待启动6:已启动
     */
    private Byte status;

    /**
     * 字段: creator
     * 说明: 申请人: mis
     */
    private String creator;

    /**
     * 字段: create_date
     * 说明: 时间戳
     */
    private Long createDate;

    /**
     * 字段: modifier
     * 说明: 修改人
     */
    private String modifier;

    /**
     * 字段: modify_date
     * 说明: 最后修改时间
     */
    private Long modifyDate;

    /**
     * 字段: finish_operator
     * 说明: 确认退货操作人
     */
    private String finishOperator;

    /**
     * 字段: finish_date
     * 说明: 退货完成时间
     */
    private Long finishDate;

    /**
     * 字段: valid
     * 说明: 删除状态：0：已删除 1：未删除
     */
    private Byte valid;
    /**
     * 字段: type
     * 说明: 退供单类型：0: 多货退供单 1: 返仓退供退供单
     */
    private Byte type;

    /**
     * 字段: source
     * 说明: 来源，1供应商，2仓内操作
     */
    private Byte source;

    /**
     * 字段: create_target
     * 说明: 创建目的，1退供，2销毁
     */
    private Byte createTarget;

    /**
     * 字段: operate_type
     * 说明: 操作类型，1退供，2销毁
     */
    private Byte operateType;

    /**
     * 字段: order_version
     * 说明: 操作类型，1-退供单直接操作库存退供，2-OMS回调完成退供
     */
    private Byte orderVersion;

    /**
     * 字段: reserve_bill_code
     * 说明: 预约单号
     */
    private String reserveBillCode;

    /**
     * 字段: receipt_code
     * 说明: 签收码
     */
    private String receiptCode;

    /**
     * 字段: return_method
     * 说明: 取货方式：1-上门自提，2-快递发货
     */
    private Byte returnMethod;

    /**
     * 字段: delivery_code
     * 说明: 快递单号，取货方式为快递发货时必填
     */
    private String deliveryCode;

    /**
     * 字段: return_date_begin
     * 说明: 预估退货开始时间
     */
    private Long returnDateBegin;

    /**
     * 字段: return_date_end
     * 说明: 预估退货结束时间
     */
    private Long returnDateEnd;

    /**
     * 字段: category_warehouse_id
     * 说明: 品类仓ID
     */
    private Long categoryWarehouseId;

    /**
     * 字段: temporary_period
     * 说明: 暂存期
     */
    private Integer temporaryPeriod;

    /**
     * 字段: destroy_period
     * 说明: 报废期
     */
    private Integer destroyPeriod;

    /**
     * 字段: return_deadline
     * 说明: 最后取货日期
     */
    private Date returnDeadline;

    /**
     * 字段: change_to_destroy_source
     * 说明: 取货单转销毁的发起来源，0-默认 1-供应商发起 2-系统自动转销毁
     */
    private Integer changeToDestroySource;

    /**
     * 字段: need_penalty
     * 说明: 是否需要处罚，1不需要，2需要
     */
    private Byte needPenalty;

    /**
     * 字段: penalty_date
     * 说明: 开始处罚日期
     */
    private Date penaltyDate;
}