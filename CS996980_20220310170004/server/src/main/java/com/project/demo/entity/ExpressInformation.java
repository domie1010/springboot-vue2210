package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递信息：(ExpressInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressInformation")
public class ExpressInformation implements Serializable {

    //ExpressInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_information_id")
    private Integer express_information_id;
   // 快递单号
   @Basic
    private String courier_number;
   // 快递公司
   @Basic
    private String courier_services_company;
   // 快递数量
   @Basic
    private Integer express_quantity;
   // 学生姓名
   @Basic
    private String student_name;
   // 学生账号
   @Basic
    private Integer student_account;
   // 学生电话
   @Basic
    private String student_telephone;
   // 快递员
   @Basic
    private Integer courier;
   // 快递员电话
   @Basic
    private String courier_telephone;
   // 配送时间
   @Basic
    private Timestamp delivery_time;
   // 公司账号
   @Basic
    private Integer company_account_number;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
