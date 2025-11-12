package org.example.farmingsellbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/10 23:06
 */
@Data
@TableName("farmings_sell_vegetables")
@ToString
public class Vegetables {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private String category;
    private String explanation;
    private String img1;
    private String img2;
    private String img3;
    private Integer stock;

}