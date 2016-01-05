package com.example.iwan.usinggson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


/**
 * Gson的基本用法
 * https://api.douban.com/v2/book/1220562
 * <p/>
 * 导入gson.jar volley.jar
 * 网络访问权限
 */

public class MainActivity extends AppCompatActivity {

    //url对象
    private String url = "https://api.douban.com/v2/book/1220562";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //请求数据的方法、
        getData();


    }


    private void getData() {
        //url请求，volley的方法
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                //输出数据，查看格式
                Log.i("info", s);

                //对输出结果进行解析
                dealData(s);

            }


        }, new Response.ErrorListener() {
            //请求失败回调方法
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        //将请求加入volley队列
        new Volley().newRequestQueue(getApplicationContext()).add(request);


    }
    //对json返回的数据结构进行解析
    private void dealData(String result) {

        //创建Gson对象
        Gson gson = new Gson();
        //将gson数据转换成Book类
        Book book = gson.fromJson(result, Book.class);

        //输出解析结果
        Log.i("info",book.getTitle()+":" + book.getPublisher()+":"
                +book.getTags().size());
    }

}
