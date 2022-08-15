package com.rikkatheworld.wan_android.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rikkatheworld.wan_android.R
import com.rikkatheworld.wan_android.data.PlaySuccess
import com.rikkatheworld.wan_android.data.bean.ArticleModel
import com.rikkatheworld.wan_android.data.bean.BannerBean
import com.rikkatheworld.wan_android.ui.PlayActions
import com.rikkatheworld.wan_android.widget.LcePage
import com.rikkatheworld.wan_android.widget.PlayAppBar
import com.rikkatheworld.wan_android.widget.banner.BannerPager

@Composable
fun HomePage(
    actions: PlayActions,
    modifier: Modifier,
    viewModel: HomePageViewModel = viewModel()
) {
    val bannerData by viewModel.bannerState.observeAsState()
    viewModel.getData()

    Column(modifier = modifier.fillMaxSize()) {
        PlayAppBar(title = stringResource(id = R.string.home_page), showBack = false)

        LcePage(playState = bannerData!!, onErrorClick = { viewModel.getData() }) {
            val data = bannerData as PlaySuccess<List<BannerBean>>
            BannerPager(items = data.data) {
                actions.enterArticle(
                    ArticleModel(title = it.title, link = it.url)
                )
            }

        }

    }
}