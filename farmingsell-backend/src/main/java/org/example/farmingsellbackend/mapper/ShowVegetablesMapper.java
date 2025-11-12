package org.example.farmingsellbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.farmingsellbackend.entity.Vegetables;

@Mapper
public interface ShowVegetablesMapper extends BaseMapper <Vegetables> {
}
