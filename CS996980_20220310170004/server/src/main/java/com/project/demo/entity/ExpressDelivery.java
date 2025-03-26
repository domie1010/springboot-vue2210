package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递配送：(ExpressDelivery)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressDelivery")
public class ExpressDelivery implements Serializable {

    //ExpressDelivery编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_delivery_id")
    private Integer express_delivery_id;
   // 快递单号
   @Basic
    private String courier_number;
   // 快递公司
   @Basic
    private String courier_services_company;
   // 快递数量
   @Basic
    private String express_quantity;
   // 学生姓名
   @Basic
    private String student_name;
   // 学生账号
   @Basic
    private Integer student_account;
   // 学生电话
   @Basic
    private String student_telephone;
   // 快递员电话
   @Basic
    private String courier_telephone;
   // 快递员
   @Basic
    private Integer courier;
   // 柜号
   @Basic
    private String cabinet_number;
   // 存放时间
   @Basic
    private Timestamp storage_time;
   // 取件码
   @Basic
    private String pick_up_code;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
