package com.github.miaosha.common;

/**
 * @ClassName ResultGen
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
public class ResultGen {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result success() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> success(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result fail(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static Result fail(ResultCode code,String message) {
        return new Result()
                .setCode(code)
                .setMessage(message);
    }
}
