package org.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * Created by wanng on 2016/11/26.
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message){
        super(message);
    }
    public RepeatKillException(String message,Throwable cause){
        super(message, cause);
    }
}
