package com.jinan.springcloud.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private String message;

    private T data;

    ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }


}
