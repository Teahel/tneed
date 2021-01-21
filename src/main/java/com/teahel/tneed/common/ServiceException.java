package com.teahel.tneed.common;

/**Service 层
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-01-21
 */

public class ServiceException  extends RuntimeException {

    private String errorMessage;

    private int errorCode;

    public ServiceException() {

    }

    public ServiceException(int code, String message) {
        this.errorMessage = message;
        this.errorCode = code;
    }
}
