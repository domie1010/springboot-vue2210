package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递收取费用：(ExpressCharges)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressCharges")
public class ExpressCharges implements Serializable {

    //ExpressCharges编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_charges_id")
    private Integer express_charges_id;
   // 寄件人
   @Basic
    private Integer sender;
   // 寄件人电话
   @Basic
    private String sender_telephone;
   // 物品类型
   @Basic
    private String item_type;
   // 物品重量
   @Basic
    private String article_weight;
   // 寄件地址
   @Basic
    private String mailing_address;
   // 价格
   @Basic
    private Integer price;
   // 总金额
   @Basic
    private String total_amount;
   // 收取时间
   @Basic
    private Timestamp collection_time;
   // 快递员
   @Basic
    private Integer courier;
   // 快递单号
   @Basic
    private String courier_number;
   // 快递公司
   @Basic
    private String courier_services_company;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
