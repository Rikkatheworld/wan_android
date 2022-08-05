package com.rikkatheworld.wan_android.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rikkatheworld.wan_android.data.bean.ArticleModel
import com.rikkatheworld.wan_android.network.PlayAndroidNetwork
import java.lang.RuntimeException

class HomeArticlePagingRepository : BaseArticlePagingRepository() {

    override fun getPagingData(query: Query) = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        HomePagingSource()
    }.flow

    suspend fun getBanner(state: MutableLiveData<PlayState>) {
        state.postValue(PlayLoading)
        val bannerResponse = PlayAndroidNetwork.getBanner()
        state.postValue(
            if (bannerResponse.errorCode == 0)
                PlaySuccess(bannerResponse.data)
            else PlayError(
                RuntimeException(
                    "response status is ${bannerResponse.errorCode}," +
                            "msg is ${bannerResponse.errorMsg}"
                )
            )
        )
    }
}

class HomePagingSource : PagingSource<Int, ArticleModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> =
        try {
            val page = params.key ?: 1
            val apiResponse = PlayAndroidNetwork.getArticleList(page)
            val articleList = apiResponse.data.datas ?: emptyList()
            val preKey = if (page > 1) page - 1 else null
            val nextKey = if (articleList.isNotEmpty()) page + 1 else null
            LoadResult.Page(articleList, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? = null
}