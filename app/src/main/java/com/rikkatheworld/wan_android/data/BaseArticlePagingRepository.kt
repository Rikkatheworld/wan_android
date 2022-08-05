package com.rikkatheworld.wan_android.data

import androidx.paging.*
import com.rikkatheworld.wan_android.data.bean.ArticleModel
import kotlinx.coroutines.flow.Flow

abstract class BaseArticlePagingRepository {
    companion object {
        const val PAGE_SIZE = 15
    }

    /**
     * 提供页面信息数据
     */
    abstract fun getPagingData(query: Query): Flow<PagingData<ArticleModel>>
}

data class Query(
    val cid: Int = -1,
    val k: String = ""
)