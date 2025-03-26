package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递公司：(CourierServicesCompany)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CourierServicesCompany")
public class CourierServicesCompany implements Serializable {

    //CourierServicesCompany编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_services_company_id")
    private Integer courier_services_company_id;
   // 公司名称
   @Basic
    private String corporate_name;
   // 负责人
   @Basic
    private String person_in_charge;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
