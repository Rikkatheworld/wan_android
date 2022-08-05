package com.rikkatheworld.wan_android.network

object PlayAndroidNetwork {

    private val homePageService = ServiceCreator.create(HomePageService::class.java)

    suspend fun getBanner() = homePageService.getBanner()

    suspend fun getArticleList(page: Int) = homePageService.getArticle(page)
}