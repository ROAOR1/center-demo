package com.example.center.common.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: wwh
 * @data: 2021/2/18
 * @Description:
 */
@Getter
@Setter
public class CommonResult<T> implements Serializable {

    @Schema(title = "状态码")
    private Integer status;

    @Schema(title = "返回信息")
    private String msg;

    @Schema(title = "返回数据")
    private T data;

    private CommonResult(T data){
        this.status = CommonEnum.SUCCESS.getStatus();
        this.msg = CommonEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private CommonResult(Integer status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> ok(){
        return new CommonResult<>(null);
    }

    public static <T> CommonResult<T> ok(T data){
        return new CommonResult<>(data);
    }

    public static <T> CommonResult<T> fail(String msg){
        return new CommonResult<>(CommonEnum.FAIL.getStatus(), msg, null);
    }

    public static <T> CommonResult<T> fail(String msg, T data){
        return new CommonResult<>(CommonEnum.FAIL.getStatus(), msg, data);
    }

    public static <T> CommonResult<T> fail(Integer status,String msg, T data){
        return new CommonResult<>(status, msg, data);
    }
}

