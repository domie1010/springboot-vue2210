package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *寄快递：(UseExpressDelivery)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "UseExpressDelivery")
public class UseExpressDelivery implements Serializable {

    //UseExpressDelivery编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_express_delivery_id")
    private Integer use_express_delivery_id;
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
    private Integer article_weight;
   // 寄件地址
   @Basic
    private String mailing_address;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
