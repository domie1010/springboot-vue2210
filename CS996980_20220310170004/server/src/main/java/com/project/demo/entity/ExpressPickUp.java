package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递取件：(ExpressPickUp)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressPickUp")
public class ExpressPickUp implements Serializable {

    //ExpressPickUp编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_pick_up_id")
    private Integer express_pick_up_id;
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
   // 学生电话
   @Basic
    private String student_telephone;
   // 快递员
   @Basic
    private Integer courier;
   // 快递员电话
   @Basic
    private String courier_telephone;
   // 取件时间
   @Basic
    private Timestamp pick_up_time;
   // 学生
   @Basic
    private Integer student;
   // 取件备注
   @Basic
    private String notes_on_taking_parts;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
