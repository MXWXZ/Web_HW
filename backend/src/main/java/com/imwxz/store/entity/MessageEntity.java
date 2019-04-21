package com.imwxz.store.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MessageEntity {
    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public MessageEntity() {
        this(0, "Success", null);
    }

    public MessageEntity(int code, String msg) {
        this(code, msg, null);
    }

    public MessageEntity(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
