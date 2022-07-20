package com.web.shopping.pojo;

public class ResultBean {

    private Integer code;
    private String message;
    private Object data;

    public static ResultBean ok(String message) {

        return new ResultBean(200, message);
    }

    public static ResultBean ok(String message, Object data) {

        return new ResultBean(200, message, data);
    }

    public static ResultBean error(String message) {

        return new ResultBean(500, message);
    }

    public static ResultBean error(String message, Object data) {

        return new ResultBean(500, message, data);
    }

    private ResultBean() {
    }

    private ResultBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
