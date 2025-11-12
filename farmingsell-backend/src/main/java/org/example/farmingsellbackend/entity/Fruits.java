package org.example.farmingsellbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * 功能：用于定义水果表格中的实体类
 * 作者：大神
 * 日期： 2025/10/30 14:47
 */
@Data
@TableName("farmings_sell_fruits")
@ToString
public class Fruits {
    @TableId(type=IdType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private String explanation;
    private String img1;
    private String img2;
    private String img3;


}