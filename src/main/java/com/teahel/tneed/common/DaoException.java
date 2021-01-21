package com.teahel.tneed.common;

/** Dao 层
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-01-21
 */

public class DaoException extends RuntimeException{

    private String errorMessage;

    private int errorCode;

    public DaoException() {

    }

    public DaoException(int code, String message) {
        this.errorMessage = message;
        this.errorCode = code;
    }

    /*
    you can use like this.
    try {
        //your code
    } catch (Exception e) {
        throw new DaoLayerException("yourerrorcode", e.getMessage());
    }
    */
}
