package com.example.center.common.resp;

/**
 * @Author: wwh
 * @Date: 2021/2/25
 * @Description:
 */
public enum CommonEnum {
    SUCCESS(1,"成功"),
    FAIL(-1, "失败");
    private Integer status;

    private String msg;

    CommonEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
