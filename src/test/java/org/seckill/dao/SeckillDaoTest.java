package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.DefaultMessageCodesResolver;

import javax.annotation.Resource;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wanng on 2016/11/24.
 */
/*
    配置spring和junit整合，junit启动时加载springIOC容器
    spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
    告诉junit spring的配置
 */
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    //注入dao 实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void queryById() throws Exception {
        long id=1001;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println("--------------------------");
        System.out.println(seckill);
    }

    @Test
    public void reduceNumber() throws Exception {
        Date killTime=new Date();
        int i=seckillDao.reduceNumber(1000L, killTime);
        System.out.println("--------------->");
        System.out.println(i);
    }


    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills=seckillDao.queryAll(0,5);
        for (Seckill seckill: seckills ) {
                System.out.println(seckill);
        }

    }
    

}