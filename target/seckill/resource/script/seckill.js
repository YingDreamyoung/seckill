/**
 * Created by wanng on 2016/12/21.
 */
/*存放主要交互逻辑js代码*/
//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)
/* seckill.detail.init(params)*/


var seckill = {
//    封装秒杀相关ajax的url
    url: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    handleSeckill: function (seckillId, node) {
        //    处理秒杀逻辑
        node.hide()
            .html('<buttn class="btn btn-primary btn-lg" id="killBtn">开始秒杀</buttn>');
        $.get(seckill.url.exposer(seckillId), {}, function (result) {
            //    再回调函数中，执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //    开启秒杀
                    //    获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.url.execution(seckillId, md5);
                    console.log("killUrl:" + killUrl);
                    $('#killBtn').one('click', function () {
                        //    绑定执行秒杀请求
                        //    1:先禁用按钮
                        $(this).addClass('disabled');
                        console.log("2发送秒杀请求执行秒杀");
                        //  2：发送秒杀请求执行秒杀
                        $.get(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                // 3:显示秒杀结果
                                console.log("显示秒杀结果-------");
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            } else {
                                console.log("难到出错了", result);
                            }
                        });
                    });
                    node.show();

                } else {
                    //    未开启秒杀
                    //    pc端与服务器端的时间不一致，但误差比较小
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countdown(seckillId, now, start, end);

                }
            } else {
                console.log('result:' + result);
            }
        });
    },
//    验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    countdown: function (seckillId, nowTime, starTime, endTime) {
        var seckillBox = $('#seckill-box');
        //    时间判断
        if (nowTime > endTime) {
            //    秒杀结束
            seckillBox.html('秒杀结束');
        } else if (nowTime < starTime) {
            console.log("秒杀未开始------", nowTime);
            //    秒杀未开始
            var killTime = new Date(starTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //    事件完成后,回调函数
            }).on('finish.countdown', function () {
                //    获取秒杀地址,控制显示逻辑，执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            //    秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },
//    详情页秒杀逻辑
    detail: {
        //    详情页初始化
        init: function (params) {
            //    手机验证和登陆，计时交互
            //    规划我们的交互流程
            //    在cookie中查询到手机号
            var killphone = $.cookie('killPhone');

            if (!seckill.validatePhone(killphone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: true//关闭键盘事件
                });

                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //    电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: "/seckill"});
                        //    刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }
            //已经登录
            //    已经登录，计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.url.now(), {}, function (result) {
                //判断result是否存在，并且result['success']为真
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //    时间判断,计时交互
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });
        }
    }
}