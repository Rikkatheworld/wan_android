package com.rikkatheworld.wan_android.network

import com.rikkatheworld.wan_android.data.bean.ArticleListModel
import com.rikkatheworld.wan_android.data.bean.BannerBean
import com.rikkatheworld.wan_android.data.bean.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface HomePageService {
    @GET("banner/json")
    suspend fun getBanner(): BaseModel<List<BannerBean>>

    @GET("article/list/{a}/{json}")
    suspend fun getArticle(@Path("a") a: Int): BaseModel<ArticleListModel>
}
