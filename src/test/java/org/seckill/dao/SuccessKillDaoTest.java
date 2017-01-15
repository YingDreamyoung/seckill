package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by wanng on 2016/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {
    @Resource
    private SuccessKillDao successKillDao;
    @Test
    public void insertSuccessKilled() throws Exception {
        long killId=1000L,userPhone=15515574188L;
        int successInsertNum=successKillDao.insertSuccessKilled(killId,userPhone);
        System.out.print(successInsertNum);

    }
    /*
    * 23:09:28.088 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.state, sk.create_time, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id=s.seckill_id where sk.seckill_id=? and sk.user_phone=?
    23:09:28.131 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 15515574188(Long)
    23:09:28.260 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
    */


    @Test
    public void queryByIdWithSeckill() throws Exception {
        long killId=1000L,userPhone=15515574188L;
        SuccessKilled queryskWithS=successKillDao.queryByIdWithSeckill(killId,userPhone);
        System.out.print(queryskWithS);
    }

}