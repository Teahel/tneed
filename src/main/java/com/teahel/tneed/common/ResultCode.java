package com.teahel.tneed.common;

/**
 * 返回值枚举
 *
 * @author litianjun
 * @date 2019/8/5 10:57
 */
public enum ResultCode {

    /**
     * 成功
     */
    OK(0, "success"),

    /**
     * 失败
     */
    ERROR(500, "system error"),

    /**
     * 未找到
     */
    NOT_FOUND(404, "not found"),

    /**
     * 没有访问权限
     */
    FORBIDDEN(403, "forbidden"),

    /**
     * 身份认证失败
     */
    UNAUTHORIZED(401, "unauthorized");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 提示信息
     */
    private final String message;

    /**
     * 构造返回值枚举
     *
     * @param code    状态码
     * @param message 信息
     */
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
