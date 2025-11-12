package org.example.farmingsellbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.farmingsellbackend.entity.Fruits;

//在使用mybatis-plus时候其实每个mapper文件中没必要都写一个mapper注释，直接在springboot启动类中直接扫描这个mapper包也行，由于这里是学习，所以便不使用扫描包了
@Mapper
public interface ShowFruitsMapper extends BaseMapper<Fruits> {
}
