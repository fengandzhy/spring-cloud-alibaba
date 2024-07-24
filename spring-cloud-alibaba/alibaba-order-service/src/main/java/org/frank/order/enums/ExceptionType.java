package org.frank.order.enums;

public enum ExceptionType {
    FLOW_EXCEPTION(-1, "限流-异常啦"),
    DEGRADE_EXCEPTION(-2, "降级-异常啦"),
    PARAM_FLOW_EXCEPTION(-3, "热点-异常啦"),
    SYSTEM_BLOCK_EXCEPTION(-4, "系统规则-异常啦"),
    AUTHORITY_EXCEPTION(-5, "认证-异常啦");

    private final int code;
    private final String msg;

    ExceptionType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
