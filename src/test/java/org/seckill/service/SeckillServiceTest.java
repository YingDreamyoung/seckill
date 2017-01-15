package org.seckill.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wanng on 2016/11/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id=1000L;
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}",seckill);

    }
//集成测试代码完整逻辑
    @Test
    public void testSeckillLogic() throws Exception {
        long id=1001L;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        //秒杀开启
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone=15515574187L;
            String md5=exposer.getMd5();
            try{
                SeckillExecution execution=seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",execution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e1){
                logger.error(e1.getMessage());
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }

    }
     //{exposed=true, md5='e78c49625becbc60355cecfd67acea16',
     // seckillId=1000, now=0, start=0, end=0}
    @Test
    public void executeSeckill() throws Exception {
        long id=1000;
        long phone=15515574187L;
        String md5="e78c49625becbc60355cecfd67acea16";
        try{
            SeckillExecution execution=seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",execution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e1){
            logger.error(e1.getMessage());
        }
    }

    @Test
    public void testExecuteSeckillProcedure() {
        long seckillId = 1001L;
        long phone = 12311111111L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService
                    .executeSeckillProcedure(seckillId, phone, md5);
           logger.info(seckillExecution.getStateInfo());
        }
    }

}