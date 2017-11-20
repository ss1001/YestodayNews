package com.example.shesong.yestodaynews.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shesong.yestodaynews.ui.INetCallback;

import org.json.JSONObject;


/**
 * Created by shesong on 2017/11/17.
 */

public class NetRequest {
    private INetCallback iNetCallback;
    private String url;
    private int num;
    private Context context;
    public NetRequest(Context context,String url,int num,INetCallback iNetCallback){
        this.context=context;
        this.num=num;
        this.url=url;
        this.iNetCallback=iNetCallback;
    }
    public void doLoadData(){
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url+num, null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                iNetCallback.netCall(response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                iNetCallback.fail();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
