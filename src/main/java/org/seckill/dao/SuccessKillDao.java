package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by wanng on 2016/11/23.
 */
public interface SuccessKillDao {
    /**
     * 插入购买明细，可过滤重复
     * @param killId
     * @param userPhone
     * @return
     */

    int insertSuccessKilled(@Param("killId") long killId,@Param("userPhone") long userPhone);

    /**
     * 通过id查询SuccessKilled，并携带秒杀产品对象实体
     * @param killId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long killId,@Param("userPhone") long userPhone);
}
