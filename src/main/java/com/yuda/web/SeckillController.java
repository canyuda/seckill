package com.yuda.web;

import com.yuda.dto.Exposer;
import com.yuda.dto.SeckillExecution;
import com.yuda.dto.SeckillResult;
import com.yuda.entity.Seckill;
import com.yuda.enums.SeckillStatEnum;
import com.yuda.exception.RepeatKillException;
import com.yuda.exception.SeckillCloseException;
import com.yuda.serivce.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @auther yuda
 * Create on 2017/11/8 0:22.
 * Project_name :   seckill
 * Package_name :   com.yuda.web
 * Description  :   TODO
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    private final Logger loggger = LoggerFactory.getLogger(this.getClass());

    /**
     * 列出商品
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //list.jsp + model = ModelAndView
        //获取列表页
        //TODO 这句话可能报错::查询异常
        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("list", seckills);
        return "list";
    }

    /**
     * 详情页
     *
     * @param seckillId 商品ID
     * @param model     模型
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "seckillId") Long seckillId, Model model) {
        loggger.debug("传入的ID:" + String.valueOf(seckillId));
        //没传id
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        //没找到相应商品,用户瞎传的id
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * ajax:json 获得ID
     *
     * @param seckillId 商品ID
     */
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {

        SeckillResult<Exposer> result;

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            loggger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }

        return result;
    }

    /**
     * 秒杀
     *
     * @param seckillId 商品ID
     * @param md5   商品对应的密钥
     * @param phone 电话
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(
            @PathVariable("seckillId") Long seckillId,
            @PathVariable("md5") String md5,
            @CookieValue(value = "killPhone", required = false) Long phone) {

        //可以使用Spring的 Valid方式
        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }

        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            result = new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            result = new SeckillResult<SeckillExecution>(true, execution);
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            result = new SeckillResult<SeckillExecution>(true, execution);
        } catch (Exception e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            result = new SeckillResult<SeckillExecution>(true, execution);
        }
        return result;
    }

    /**
     * 获取服务器时间
     *
     * @return
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date date = new Date();
        return new SeckillResult<Long>(true, date.getTime());
    }

    /**
     *  改变内容页面
     * @param seckillId 商品ID
     * @param model 模型
     * @return
     */
    //TODO
    @RequestMapping(value = "/{seckillId}/change", method = RequestMethod.GET)
    public String change(@PathVariable(value = "seckillId") Long seckillId, Model model) {
        loggger.debug("传入的ID:" + String.valueOf(seckillId));
        //没传id
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        //没找到相应商品,用户瞎传的id
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "change";
    }
}

