package com.yuda.serivce.impl;

import com.yuda.dao.RedisDao;
import com.yuda.dao.SeckillDao;
import com.yuda.dao.SuccessKilledDao;
import com.yuda.dto.Exposer;
import com.yuda.dto.SeckillExecution;
import com.yuda.entity.Seckill;
import com.yuda.entity.SuccessKilled;
import com.yuda.enums.SeckillStatEnum;
import com.yuda.exception.RepeatKillException;
import com.yuda.exception.SeckillCloseException;
import com.yuda.exception.SeckillException;
import com.yuda.serivce.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/8 0:25.
 * Project_name :   seckill
 * Package_name :   com.yuda.serivce.impl
 * Description  :   TODO
 */

/**
 * 事务的优点和注意事项:
 * 1.开发团队达成一致约定,明确标注事务方法的编程风格
 * 2.保证事务方法的执行时间尽可能短,不要穿插远程调用或网络请求的操作,需要把这些剥离到事务方法外
 * 3.不是所有方法都要放到事务中,如只有一条的修改,只读操作不需要事务控制.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final Logger loggger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;

    //混淆md5
    private final String slat = "abcafafafa;jo;213;ofj;";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * 获得秒杀URL
     *
     * @param seckillId
     * @return
     */
    @Override
    @Transactional
    public Exposer exportSeckillUrl(long seckillId) {
        //通过Redis进行缓存 使用超时来维护redis
        //1. 访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        //如果不为空,就跳过数据库查找走Redis
        if (seckill == null) {
            //访问数据库
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                //数据库查不到
                return new Exposer(false, seckillId);
            } else {
                //查询到了放入redis
                redisDao.setSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), endTime.getTime());
        }
        //Md5转换为字符串,不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        //判断md5
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime = new Date();
        try {
            //记录购买记录
            int insertCount = successKilledDao.insertSucceccKilled(seckillId, userPhone);

            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                //减库存
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //rollback
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //Commit
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException ee) {
            throw ee;
        } catch (RepeatKillException eee) {
            throw eee;
        } catch (Exception e) {
            loggger.error(e.getMessage(), e);
            //所有编译期异常,转换为运行期异常
            throw new SeckillException("seckill inner error" + e.getMessage());
        }
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
