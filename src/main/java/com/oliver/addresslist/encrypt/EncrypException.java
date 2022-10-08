package com.oliver.addresslist.encrypt;

public class EncrypException extends Exception {
    private String code;
    private String msg;

    public EncrypException(String code, Exception exception) {
        this.code = code;
        this.msg = exception.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
