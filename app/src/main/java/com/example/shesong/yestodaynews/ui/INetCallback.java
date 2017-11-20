package com.example.shesong.yestodaynews.ui;

import org.json.JSONObject;

/**
 * Created by shesong on 2017/11/17.
 */

public interface INetCallback {
    public void netCall(JSONObject jsonObject);
    public void fail();
}
