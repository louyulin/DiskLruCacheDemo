package com.example.louyulin.diskdemo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by dllo on 18/1/9.
 */

public interface  Api {


    @GET("article/list/{page}/json")
    Observable<HomeArticalBean> getHomeList(@Path("page") String page);

}
