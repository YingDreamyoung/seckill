package org.seckill.dto;

/**
 * Created by wanng on 2016/12/14.
 */

//所有ajax请求放回类型,封装json 结果
public class SeckillResult<T> {
    private Boolean success;
    private T data;
    private String err;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public SeckillResult(Boolean success, String err) {

        this.success = success;
        this.err = err;
    }

    public SeckillResult(Boolean success, T data) {

        this.success = success;
        this.data = data;
    }
}
