--创建数据库脚本
create database seckill;
--使用数据库
use seckill;
--创建秒杀库存表
create table seckill(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
name varchar(120) not null comment '商品名称',
number int not null comment '库存数量',
start_time timestamp not null comment '秒杀开启时间',
end_time timestamp not null comment '秒杀结束时间',
create_time timestamp not null default current_timestamp comment '创建时间',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';

--初始化表
insert into
    seckill(name,number,start_time,end_time)
values
    ('100元秒杀iphone 6s',100,'2016-11-22 00:00:00','2017-11-23 00:00:00'),
    ('200元秒杀iphone 5s',100,'2016-11-22 00:00:00','2017-11-23 00:00:00'),
    ('300元秒杀iphone 4s',100,'2016-11-22 00:00:00','2017-11-23 00:00:00'),
    ('400元秒杀iphone 3s',100,'2016-11-22 00:00:00','2017-11-23 00:00:00');

--秒杀成功明细表
--用户登录认证相关信息

create table success_killed(
seckill_id bigint not null comment '商品id',
user_phone bigint not null comment '用户手机',
state tinyint not null default -1 comment '状态表示： -1:无效 0：成功  1：已付款',
create_time timestamp not null comment '创建时间',
primary key(seckill_id,user_phone),
key idx_create_time(create_time)
)engine=InnoDB default charset=utf8 comment='秒杀成功明细表';


        update
            seckill
        set
            end_time='2017-11-23 00:00:00'
