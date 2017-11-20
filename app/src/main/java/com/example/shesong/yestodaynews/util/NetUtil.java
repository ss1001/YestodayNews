package com.example.shesong.yestodaynews.util;

import com.example.shesong.yestodaynews.net.NetEnv;

import java.util.Random;

/**
 * Created by shesong on 2017/11/16.
 */

public class NetUtil {
    public static String getPhotoUrl(){
        Random random=new Random();
        int index=random.nextInt()%10;
        return NetEnv.NET_SERVER+index+".jpg";
    }
}
