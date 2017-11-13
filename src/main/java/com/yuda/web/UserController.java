package com.yuda.web;

import com.yuda.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @auther yuda
 * Create on 2017/11/12 19:34.
 * Project_name :   seckill
 * Package_name :   com.yuda.web
 * Description  :   TODO
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final Logger loggger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(User user, Model model) {
        System.out.println(user);
        System.out.println(user);
        model.addAttribute("user", user);
        return "user";
    }

}
