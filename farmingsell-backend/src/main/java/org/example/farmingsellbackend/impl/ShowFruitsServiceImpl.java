package org.example.farmingsellbackend.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.farmingsellbackend.entity.Fruits;
import org.example.farmingsellbackend.mapper.ShowFruitsMapper;
import org.example.farmingsellbackend.service.ShowFruitsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/11 22:52
 */
@Service
public class ShowFruitsServiceImpl extends ServiceImpl<ShowFruitsMapper, Fruits> implements ShowFruitsService {
    @Override
    public List<Fruits> selectAllFruits() {
//        大神写着写着发现，这是一个选择条件的语句，但是我用于我的全部数据展示的时候并不需要进行筛选，我只需要拿到我的所有数据即可，所以说直接获取就行了
        // 这里创建一个新的QueryWrapper条件构造器，用代码动态构建sql查询的where子句
//        QueryWrapper<Fruits> queryWrapper = new QueryWrapper<>();
        //下面就相当于是一个sql的选择语句。后面括号中的内容，第一个带引号的是指的是数据库中的列名字，第二个是你传进来的参数，进行判断
        return baseMapper.selectList(null);
    }
}