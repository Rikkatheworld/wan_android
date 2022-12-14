package com.rikkatheworld.wan_android.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rikkatheworld.wan_android.ui.PlayActions
import com.rikkatheworld.wan_android.data.CourseTabs
import com.rikkatheworld.wan_android.data.bean.ArticleModel
import com.rikkatheworld.wan_android.ui.home.HomePage
import java.util.*

@Composable
fun MainPage(
    actions: PlayActions,
    viewModel: HomeViewModel = viewModel()
) {
    val position by viewModel.position.observeAsState()
    val tabs = CourseTabs.values()

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary),
                        icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                        label = { Text(stringResource(tab.title).uppercase(Locale.ROOT)) },
                        selected = tab == position,
                        onClick = { viewModel.onPositionChanged(tab) },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        Crossfade(targetState = position) { screen ->
            when (screen) {
                CourseTabs.HOME_PAGE -> HomePage(actions, modifier)
                CourseTabs.PROJECT -> ProjectPage(actions.enterArticle, modifier)
                CourseTabs.OFFICIAL_ACCOUNT -> OfficialAccountPage(actions.enterArticle, modifier)
                CourseTabs.MINE -> ProfilePage(actions)
            }
        }
    }
}

@Composable
fun ProfilePage(actions: PlayActions) {
}

@Composable
fun OfficialAccountPage(enterArticle: (ArticleModel) -> Unit, modifier: Modifier) {
}

@Composable
fun ProjectPage(enterArticle: (ArticleModel) -> Unit, modifier: Modifier) {
}