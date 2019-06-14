package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /**
     * 状态标识
     */
    private boolean flag;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static Result ok() {
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), StatusCodeEnum.OK.getDefaultMessage());
    }

    public static Result ok(String message) {
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), message);
    }

    public static Result ok(Object data) {
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), StatusCodeEnum.OK.getDefaultMessage(), data);
    }

    public static Result ok(String message, Object data) {
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), message, data);
    }

    public static Result ok(Integer code, String message) {
        return new Result(true, code, message);
    }

    public static Result ok(Integer code, String message, Object data) {
        return new Result(true, code, message, data);
    }

    public static Result error(Integer code, String message) {
        return new Result(false, code, message);
    }


}
