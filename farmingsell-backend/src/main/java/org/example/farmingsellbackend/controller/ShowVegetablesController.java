package org.example.farmingsellbackend.controller;

import org.example.farmingsellbackend.common.AuthAccess;
import org.example.farmingsellbackend.common.Result;
import org.example.farmingsellbackend.entity.Vegetables;
import org.example.farmingsellbackend.service.ShowVegetablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/12 14:57
 */
@RestController
@CrossOrigin
@RequestMapping("/show")
public class ShowVegetablesController {
    @Autowired
    ShowVegetablesService showVegetablesService;
    @AuthAccess
    @GetMapping(value="/vegetable", produces = "application/json")
    public Result selectAllVegetables(){
        List<Vegetables> vegetablesList=showVegetablesService.list();
        return Result.success(vegetablesList);
    }

}