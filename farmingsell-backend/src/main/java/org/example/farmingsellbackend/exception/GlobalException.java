package org.example.farmingsellbackend.exception;

import org.example.farmingsellbackend.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e){

        // 核心修正点：将 String 类型的 code 转换为 Integer
        Integer errorCode = Integer.parseInt(e.getCode());

        // 返回 Result.error，现在传入的是 Integer 类型
        return Result.error(errorCode, e.getMessage());
    }

    // 全局兜底异常处理器
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e){
        e.printStackTrace();
        return Result.error(500, "系统错误：" + e.getMessage());
    }
}