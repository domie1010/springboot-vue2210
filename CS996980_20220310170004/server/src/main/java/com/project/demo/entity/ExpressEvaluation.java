package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递评价：(ExpressEvaluation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressEvaluation")
public class ExpressEvaluation implements Serializable {

    //ExpressEvaluation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_evaluation_id")
    private Integer express_evaluation_id;
   // 评价编号
   @Basic
    private String evaluation_number;
   // 评价分数
   @Basic
    private String evaluation_score;
   // 评价人
   @Basic
    private Integer evaluator;
   // 评价时间
   @Basic
    private Timestamp evaluation_time;
   // 评价详情
   @Basic
    private String evaluation_details;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
