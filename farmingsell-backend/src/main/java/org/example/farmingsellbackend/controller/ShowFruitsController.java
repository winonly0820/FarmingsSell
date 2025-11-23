package org.example.farmingsellbackend.controller;

import org.example.farmingsellbackend.common.AuthAccess;
import org.example.farmingsellbackend.common.Result;
import org.example.farmingsellbackend.entity.Fruits;
import org.example.farmingsellbackend.service.ShowFruitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/11 22:24
 */
@RestController
@CrossOrigin
@RequestMapping("/show")
public class ShowFruitsController {
    @Autowired
    ShowFruitsService showFruitsService;
//    json数据序列化失败，我在request文件中添加了请求头，在这里强制返回了json
    @AuthAccess
    @GetMapping(value = "/fruit", produces = "application/json")
//    下面result后面的那个方法是service层的方法
    public Result selectAllFruits(){
        List<Fruits> fruitsList=showFruitsService.list();
        return Result.success(fruitsList);
    }
}
