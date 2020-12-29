package com.teahel.tneed.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 返回格式封装
 *
 * @author litianjun
 * @date 2019/10/17 9:28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultUtils extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 无参构造
     */
    public ResultUtils() {
        super();
    }

    /**
     * 根据编号，信息，返回对象构造返回封装
     *
     * @param code    状态码
     * @param message 信息
     * @param object  对象
     */
    public ResultUtils(int code, String message, Object object) {
        put("code", code);
        put("message", message);
        put("data", object);
    }

    /**
     * 根据返回状态、返回对象构造返回封装
     *
     * @param result 返回状态
     * @param object 对象
     */
    public ResultUtils(ResultCode result, Object object) {
        put("code", result.getCode());
        put("message", result.getMessage());
        put("data", object);
    }

    /**
     * 成功
     *
     * @return 成功返回封装
     */
    public static ResultUtils ok() {
        return new ResultUtils(ResultCode.OK, "");
    }

    /**
     * 添加数据
     *
     * @param key   key
     * @param value value
     * @return 通用返回
     */
    @Override
    public ResultUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 成功
     *
     * @param object 对象
     * @return 成功返回封装
     */
    public static ResultUtils ok(Object object) {
        return new ResultUtils(ResultCode.OK, object);
    }

    /**
     * 成功
     *
     * @param object 对象
     * @param result 返回结果
     * @return 成功返回封装
     */
    public static ResultUtils ok(ResultCode result, Object object) {
        return new ResultUtils(result, object);
    }

    /**
     * 成功
     *
     * @param result 返回结果
     * @return 成功返回封装
     */
    public static ResultUtils ok(ResultCode result) {
        return new ResultUtils(result, "");
    }

    /**
     * 失败
     *
     * @param object 对象
     * @return 失败返回封装
     */
    public static ResultUtils error(Object object) {
        return new ResultUtils(ResultCode.ERROR, object);
    }

    /**
     * 失败
     *
     * @param message 提示信息
     * @return 失败返回封装
     */
    public static ResultUtils error(String message) {
        return new ResultUtils(ResultCode.ERROR.getCode(), message, "");
    }

    /**
     * 失败
     *
     * @param object 对象
     * @param result 返回结果
     * @return 失败返回封装
     */
    public static ResultUtils error(ResultCode result, Object object) {
        return new ResultUtils(result, object);
    }

    /**
     * 失败
     *
     * @param result 返回结果
     * @return 失败返回封装
     */
    public static ResultUtils error(ResultCode result) {
        return new ResultUtils(result, "");
    }

    /**
     * 失败
     *
     * @param e 异常
     * @return 失败返回封装
     */
    public static ResultUtils error(Throwable e) {
        return new ResultUtils(ResultCode.ERROR.getCode(), e.getMessage(), "");
    }
}
