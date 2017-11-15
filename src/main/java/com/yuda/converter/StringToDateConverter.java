package com.yuda.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther yuda
 * Create on 2017/11/12 20:06.
 * Project_name :   seckill
 * Package_name :   com.yuda.converter
 * Description  :   TODO
 */
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
