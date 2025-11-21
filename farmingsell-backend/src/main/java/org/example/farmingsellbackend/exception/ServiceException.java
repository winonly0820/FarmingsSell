package org.example.farmingsellbackend.exception;

import lombok.Getter;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/20 0:37
 */
@Getter
public class ServiceException extends RuntimeException {
    private final String code;
    public ServiceException(String message) {
        super(message);
        this.code="500";
    }
    public ServiceException(String code,String msg) {
        super(msg);
        this.code = code;
    }

}