package com.yuda.enums;

/**
 * @auther yuda
 * Create on 2017/11/8 14:44.
 * Project_name :   seckill
 * Package_name :   com.yuda.enums
 * Description  :   用枚举表示数据字典
 */
public enum SeckillStatEnum {
    SUCCESS(1, "成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;
    private String stateinfo;
    SeckillStatEnum(int state, String stateinfo) {
        this.state = state;
        this.stateinfo = stateinfo;
    }
    public int getState() {
        return state;
    }
    public String getStateinfo() {
        return stateinfo;
    }
    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum statEnum : values()) {
            if (statEnum.getState() == index) {
                return statEnum;
            }
        }
        return null;
    }
}
