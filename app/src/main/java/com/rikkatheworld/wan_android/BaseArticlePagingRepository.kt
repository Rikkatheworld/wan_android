package com.rikkatheworld.wan_android

import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import java.lang.RuntimeException

abstract class BaseArticlePagingRepository {
    companion object {
        const val PAGE_SIZE = 15
    }

    abstract fun getPagingData(query: Query): Flow<PagingData<ArticleModel>>
}

data class Query(
    val cid: Int = -1,
    val k: String = ""
)

class HomeArticlePagingRepository : BaseArticlePagingRepository() {

    override fun getPagingData(query: Query) = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        HomePagingSource()
    }.flow()

    suspend fun getBanner(state: MutableLiveData<PlayState>) {
        state.postValue(PlayState.PlayLoading)
        val bannerResponse = PlayAndroidNetwork.getBanner()
        if (bannerResponse.errorCode == 0) {
            val bannerList = bannerResponse.data
            bannerList.forEach {
                it.data = it.imagePath
            }
            state.postValue(PlaySuccess(bannerList))
        } else {
            state.postValue(
                PlayError(
                    RuntimeException(
                        "response status is ${bannerResponse.error}" +
                                " msg is ${bannerResponse.errorMsg}"
                    )
                )
            )
        }
    }
}

class HomePagingSource : PagingSource<Int, ArticleModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> =
        try {
            val page = params.key ?: 1
            val apiResponse = PlayAndroidNetwork.getArticleList(page)
            val articleList = apiResponse.data.datas
            val preKey = if (page > 1) page - 1 else null
            val nextKey = if (articleList.isNotEmpty()) page + 1 else null
            LoadResult.Page(articleList, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? = null
}