package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递收取：(ExpressCollection)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressCollection")
public class ExpressCollection implements Serializable {

    //ExpressCollection编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_collection_id")
    private Integer express_collection_id;
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
   // 快递员
   @Basic
    private Integer courier;
   // 上门时间
   @Basic
    private Timestamp door_time;
   // 快递公司
   @Basic
    private Integer courier_services_company;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
