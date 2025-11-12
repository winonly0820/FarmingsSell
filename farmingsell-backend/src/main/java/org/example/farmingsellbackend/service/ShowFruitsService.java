package org.example.farmingsellbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.farmingsellbackend.entity.Fruits;

import java.util.List;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/11 22:27
 */
public interface  ShowFruitsService extends IService<Fruits> {
    List<Fruits> selectAllFruits();
}