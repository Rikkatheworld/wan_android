package com.rikkatheworld.wan_android.widget

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.statusBarsHeight

/**
 * 用于实现通用的标题栏
 *
 * @param title 标题
 * @param showBack 是否有返回键按钮
 * @param click 点击回调
 * @param showRight 是否展示右边
 * @param rightImg 左边的图片
 * @param rightClick 点击左边的回调
 */
@Composable
fun PlayAppBar(
    title: String,
    showBack: Boolean,
    click: (() -> Unit)? = null,
    showRight: Boolean = false,
    rightImg: ImageVector = Icons.Rounded.MoreVert,
    rightClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
        Spacer(Modifier.statusBarsHeight())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (showBack && click != null) {
                IconButton(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start), onClick = click
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "back")
                }
            }

            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (showRight && rightClick != null) {
                IconButton(
                    modifier = Modifier.wrapContentWidth(Alignment.End),
                    onClick = rightClick
                ) {
                    Icon(imageVector = rightImg, contentDescription = "more")
                }
            }
        }
    }
}