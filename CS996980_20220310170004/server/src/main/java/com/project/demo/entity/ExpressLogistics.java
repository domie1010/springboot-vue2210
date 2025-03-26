package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递物流：(ExpressLogistics)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressLogistics")
public class ExpressLogistics implements Serializable {

    //ExpressLogistics编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_logistics_id")
    private Integer express_logistics_id;
   // 寄件人
   @Basic
    private Integer sender;
   // 快递单号
   @Basic
    private String courier_number;
   // 快递公司
   @Basic
    private String courier_services_company;
   // 发货地
   @Basic
    private String place_of_shipment;
   // 目的地
   @Basic
    private String destination;
   // 公司账号
   @Basic
    private Integer company_account_number;
   // 物流信息
   @Basic
    private String logistics_information;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
