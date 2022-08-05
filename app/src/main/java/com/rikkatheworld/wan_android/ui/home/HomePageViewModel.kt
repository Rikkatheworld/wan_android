package com.rikkatheworld.wan_android.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rikkatheworld.wan_android.data.BaseArticlePagingRepository
import com.rikkatheworld.wan_android.data.HomeArticlePagingRepository
import com.rikkatheworld.wan_android.data.PlayState
import com.rikkatheworld.wan_android.data.Query
import com.rikkatheworld.wan_android.ui.BaseArticleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomePageViewModel(application: Application) : BaseArticleViewModel(application) {
    override val repositoryArticle: BaseArticlePagingRepository
        get() = HomeArticlePagingRepository()

    private var bannerJob: Job? = null

    private val _bannerState = MutableLiveData<PlayState>()
    val bannerState: LiveData<PlayState>
        get() = _bannerState

    fun getData() {
        getBanner()
        searchArticle(Query())
    }

    private fun getBanner() {
        bannerJob?.cancel()
        bannerJob = viewModelScope.launch(Dispatchers.IO) {
            (repositoryArticle as HomeArticlePagingRepository).getBanner(_bannerState)
        }
    }

}