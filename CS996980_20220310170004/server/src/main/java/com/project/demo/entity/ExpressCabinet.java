package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *快递柜：(ExpressCabinet)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExpressCabinet")
public class ExpressCabinet implements Serializable {

    //ExpressCabinet编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "express_cabinet_id")
    private Integer express_cabinet_id;
   // 柜号
   @Basic
    private String cabinet_number;
   // 格口类型
   @Basic
    private String lattice_type;
   // 地址
   @Basic
    private String address;
   // 图片
   @Basic
    private String picture;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
