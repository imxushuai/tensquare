package entity;

import lombok.Getter;

/**
 * 状态码枚举类
 */
@Getter
public enum StatusCodeEnum {

    OK(20000, "请求成功"),
    ERROR(20001, "请求失败"),
    LOGIN_ERROR(20002, "用户名或密码错误"),
    ACCESS_ERROR(20003, "权限不足"),
    REMOTE_ERROR(20004, "远程调用失败"),
    REP_ERROR(20005, "重复操作"),
    ;

    private int statusCode;
    private String defaultMessage;

    StatusCodeEnum(int statusCode, String defaultMessage) {
        this.statusCode = statusCode;
        this.defaultMessage = defaultMessage;
    }
}
