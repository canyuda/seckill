package com.yuda.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @auther yuda
 * Create on 2017/11/11 23:36.
 * Project_name :   seckill
 * Package_name :   com.yuda.okhttp
 * Description  :   TODO
 */
public class OkHttpDemo {

    @Test
    public void demo1() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://juejin.im/zhuanlan/backend")
                .build();
        try {
            Response response = client.newCall(request).execute();
            File filePath = new File("H:\\MyProject\\test\\seckill\\src\\main\\webapp\\WEB-INF\\okhttp");
            File file = new File(filePath, "index1.html");
            if (!file.exists()) {
                file.createNewFile();
            }
            String string = response.body().string();
            FileCopyUtils.copy(string, new FileWriter(file));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String method1(String str){
        StringBuilder sb= new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

}
