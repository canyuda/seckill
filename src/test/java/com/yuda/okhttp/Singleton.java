package com.yuda.okhttp;

import java.util.Collections;

/**
 * @auther yuda
 * Create on 2017/11/14 14:54.
 * Project_name :   seckill
 * Package_name :   com.yuda.okhttp
 * Description  :   TODO
 */
public class Singleton {
    private static Singleton singleton = null;
    private Singleton(){}
    public static Singleton getInstance() {
        if (singleton==null){
            synchronized (singleton){
                if (singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
