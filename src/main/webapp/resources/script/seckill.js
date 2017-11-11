//模块化
//seckill.URL.XXX()
//seckill.detail.XXX()
var seckill = {
    //所有的请求路径
    URL: {
        base: function () {
            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0,index+1);
            return result;
        },
        now: function () {
            return seckill.URL.base() + "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return seckill.URL.base() + "/seckill/" + seckillId + "/exposer";
        },
        killUrl: function (seckillId, md5) {
            return seckill.URL.base() + "/seckill/" + seckillId + "/" + md5 + "/execution";
        }
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    //秒杀操作
    handlerSeckillkill: function (seckillId, node) {
        //地址,显示,秒杀
        node.hide().html('<button class="btn  btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //
            if (result && result['success']) {
                //成功
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开始
                    //获取地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.killUrl(seckillId, md5);
                    console.log("秒杀地址" + killUrl);
                    $("#killBtn").one('click', function () {
                        //绑定执行秒杀请求的操作
                        $(this).addClass('disabled');
                        //发送请求
                        $.post(killUrl, {}, function (result) {
                            //发送请求执行秒杀
                            //分析结果
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //显示秒杀结果
                                node.html("<span class='label label-success'>" + stateInfo + "</span>");
                            }

                        });
                    });
                    node.show();
                } else {
                    //未开启
                    //如果用户开启计时面板,时间久了,各种机器的时间是有差异的
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.mycountdown(seckillId, now, start, end);
                }
            } else {
                //失败
                console.log(result['data']);
            }
        });
    },
    //时间
    mycountdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $("#seckill-box");
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html("秒杀结束");
        } else if (nowTime < startTime) {
            //秒杀未开始
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                //时间变化时候做日期输出
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //时间完成后回调事件,获取秒杀地址,控制显示逻辑,执行秒杀
                //页面处于倒计时,然后时间到了
                seckill.handlerSeckillkill(seckillId, seckillBox);
            })
        } else {
            //秒杀开始
            //时间本来就到了,用户进入秒杀页面
            seckill.handlerSeckillkill(seckillId, seckillBox);
        }
    },
    //详情页
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证相关操作,计时交互
            //规划交互流程
            //cookie中取得手机号
            var killPhone = $.cookie('killPhone');
            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //绑定Phone
                //控制输出
                seckill.detail.editPhone();
            }
            //已经登陆
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];

            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断,计时交互
                    seckill.mycountdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });

            $("#clearPhone").click(function () {
                $.cookie('killPhone', "", {expires: 0, path: '/seckill'});
                window.location.reload();
            });

            $("#changePhone").click(function () {
                seckill.detail.editPhone();
            });

            $("#killPhoneKey").val($.cookie('killPhone'));
        },
        editPhone: function () {
            var killPhoneModal = $("#killPhoneModal");
            killPhoneModal.modal({
                show: true,//显示
                backdrop: 'static',//禁止位置关闭
                keyboard: false//关闭键盘事件
            });
            $("#killPhoneBtn").click(function () {
                var inputPhone = $("#killPhoneKey").val();
                if (seckill.validatePhone(inputPhone)) {
                    //电话写入cookie
                    $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                    //刷新页面
                    window.location.reload();
                } else {
                    $("#killPhoneMessage").hide()
                        .html('<label class="label label-danger">手机号错误</label>')
                        .show(300);
                }
            });
        }
    }
};
