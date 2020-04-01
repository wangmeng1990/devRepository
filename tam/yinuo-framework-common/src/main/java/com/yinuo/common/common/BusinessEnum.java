package com.yinuo.common.common;

/**
 * 业务异常枚举类
 */
public enum BusinessEnum {
    /**
     * 示例- 该商品已下架
     */
    PRODUCT_LOWER_ERROR("603","该商品已下架");

    ;

    /**
     * 错误码
     */
    private String errCode;
    /**
     * 错误描述
     */
    private String errMsg;

    BusinessEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
