package org.example.farmingsellbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.farmingsellbackend.entity.Vegetables;

import java.util.List;

public interface ShowVegetablesService extends IService <Vegetables> {
    List<Vegetables> selectAllVegetables();
}
