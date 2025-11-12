package org.example.farmingsellbackend.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 手动添加 Getter 方法（序列化必须）
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    // 手动实现 Builder 内部类
    public static class Builder<T> {
        private Integer code;
        private String msg;
        private T data;

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            Result<T> result = new Result<>();
            result.code = this.code;
            result.msg = this.msg;
            result.data = this.data;
            return result;
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    // 原有方法（调用方式完全不变）
    public static <T> Result<T> success(T data) {
        return Result.<T>builder().code(200).msg("成功").data(data).build();
    }

    public static <T> Result<T> success() {
        return Result.<T>builder().code(200).msg("成功").data(null).build();
    }

    public static <T> Result<T> error(String msg) {
        return Result.<T>builder().code(500).msg(msg).data(null).build();
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return Result.<T>builder().code(code).msg(msg).data(null).build();
    }
}