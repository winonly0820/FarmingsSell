package org.example.farmingsellbackend.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.farmingsellbackend.entity.Vegetables;
import org.example.farmingsellbackend.mapper.ShowVegetablesMapper;
import org.example.farmingsellbackend.service.ShowVegetablesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/12 14:53
 */
@Service
public class ShowVegetablesServiceImpl extends ServiceImpl<ShowVegetablesMapper, Vegetables>  implements ShowVegetablesService {
    @Override
    public List<Vegetables> selectAllVegetables() {
        return baseMapper.selectList(null);
    }
}