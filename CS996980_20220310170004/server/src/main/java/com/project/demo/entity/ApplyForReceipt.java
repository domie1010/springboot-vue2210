package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *申请接单：(ApplyForReceipt)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ApplyForReceipt")
public class ApplyForReceipt implements Serializable {

    //ApplyForReceipt编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_for_receipt_id")
    private Integer apply_for_receipt_id;
   // 快递员
   @Basic
    private Integer courier;
   // 快递员电话
   @Basic
    private String courier_telephone;
   // 申请时间
   @Basic
    private Timestamp application_time;
   // 快递公司
   @Basic
    private Integer courier_services_company;
   // 申请原因
   @Basic
    private String reason_for_application;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
