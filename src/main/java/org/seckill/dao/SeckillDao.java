package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by wanng on 2016/11/23.
 */


public interface SeckillDao {
    /**
     * 减少库存
     * @param seckillId
     * @param killTime
     * @return 如果返回的直>1,表示更新记录的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 通过id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);



    /**
     * 查询秒杀对象
     * @param offset
     * @param limit
     * @return
     */

    //Parameter 'off' not found. Available parameters are [0, 1, param1, param2]
    //java 没有保存形参记录，--->query(arg0,arg1)

    List<Seckill> queryAll(@Param("offset")int arg0, @Param("limit")int limit);
    /*
    *使用存储过程执行秒杀
    * @paramMap
    * */
    void killByProcedure(Map<String,Object> paramMap);
}

