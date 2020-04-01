package com.yinuo.common.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("通用响应返回对象")
@Data
@SuppressWarnings("unchecked")
public class HttpResult<T> implements Serializable {

    /**
     * 成功标示
     */
	@ApiModelProperty("是否请求成功")
    private boolean success;
    /**
     * 返回状态码
     */
	@ApiModelProperty("状态码")
    private String errCode;
    /**
     * 返回对象
     */
	@ApiModelProperty("返回结果")
    private T data;
    /**
     * 返回错误信息
     */
    @ApiModelProperty("错误信息")
    private String errMsg;

    // 构造器开始
    @ApiModelProperty("返回结果")
    private Map<String,Object> dataMap=new HashMap<String, Object>();



    public static <T> HttpResult success(long total, List<T> data){
        ListResult<T> ListResult = new ListResult<T>();
        ListResult.setTotal(total);
        ListResult.setRows(data);
        return HttpResult.success(ListResult);
    }

    /**
     * 无参构造器
     */
    public HttpResult() {
        this.errCode = "200";
        this.success = true;
    }
    /**
     * 返回成功的实体
     * @param obj
     */
    public HttpResult(T obj) {
        this.errCode = "200";
        this.data = obj;
        this.success = true;
    }

    /**
     * 返回错误信息
     * @param errCodeAndMsg
     */
    public HttpResult(ErrCodeAndMsg errCodeAndMsg) {
        this.success = false;
        this.errCode = errCodeAndMsg.getErrCode();
        this.errMsg = errCodeAndMsg.getErrMsg();
    }

    /**
     * 返回错误信息
     * @param code
     * @param message
     */
    public HttpResult(String code, String message) {
        this.success = false;
        this.errCode = code;
        this.errMsg = message;
    }

    // 构造器结束

    /**
     * 成功直接返回数据和状态
     * @param data
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> success(T data){
        return new HttpResult<T>(data);
    }

    /**
     *
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> success(){
        return new HttpResult();
    }
    /**
     * 失败的时候调用
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> failure(String code, String msg){
        return  new HttpResult<T>(code,msg);
    }

    /**
     * 失败的时候调用
     * @param errCodeAndMsg
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> failure(ErrCodeAndMsg errCodeAndMsg){
        return  new HttpResult<T>(errCodeAndMsg);
    }

    public static<T> HttpResult<T> failure(String msg){
        return  new HttpResult<T>("700",msg);
    }
    public static<T> HttpResult<T> failure(){
        return   new HttpResult<T>("701","操作失败");
    }

    public static<T> HttpResult<T> retCol(int ret) {
		if(ret==1){
		return	success();
		}else {
	    return failure(ErrCodeAndMsg.SERVER_ERROR.getErrCode(),ErrCodeAndMsg.SERVER_ERROR.getErrMsg());
		}
	}

	 
}

