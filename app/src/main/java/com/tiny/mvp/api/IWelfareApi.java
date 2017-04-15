package com.tiny.mvp.api;

import com.tiny.mvp.api.bean.WelfarePhotoList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

import static com.tiny.mvp.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 *
 */

public interface IWelfareApi {

    /**
     * 获取福利图片
     * eg: http://gank.io/api/data/福利/10/1
     *
     * @param page 页码
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("/api/data/福利/10/{page}")
    Observable<WelfarePhotoList> getWelfarePhoto(@Path("page") int page);


}
