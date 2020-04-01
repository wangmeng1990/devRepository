package com.yinuo.gateway.zuulResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult {

    //操作是否成功
    boolean success;

    //提示信息
    String message;

    public ResponseResult(boolean success,String message) {
        this.success = success;
        this.message = message;
    }
}
