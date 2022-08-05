package com.rikkatheworld.wan_android.widget

import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.rikkatheworld.wan_android.data.PlayError
import com.rikkatheworld.wan_android.data.PlayLoading
import com.rikkatheworld.wan_android.data.PlayState
import com.rikkatheworld.wan_android.data.PlaySuccess
import com.rikkatheworld.wan_android.R

/**
 * 不同状态下显示的页面
 *
 * @param playState
 * @param onErrorClick
 * @param content
 */
@Composable
fun LcePage(
    playState: PlayState,
    onErrorClick: () -> Unit,
    content: @Composable () -> Unit
) {
    when (playState) {
        PlayLoading -> LoadingContent()
        is PlayError -> ErrorContent(onErrorClick = onErrorClick)
        is PlaySuccess<*> -> content()
    }
}

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            { ProgressBar(it) }, modifier = Modifier
                .width(200.dp)
                .height(110.dp)
        ) {
            it.indeterminateDrawable =
                AppCompatResources.getDrawable(
                    it.context,
                    R.drawable.loading_animation
                )
        }
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onErrorClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(vertical = 50.dp),
            painter = painterResource(id = R.drawable.bad_network_image),
            contentDescription = "网络加载失败"
        )
        Button(onClick = onErrorClick) {
            Text(text = stringResource(id = R.string.bad_network_view_tip))
        }
    }
}