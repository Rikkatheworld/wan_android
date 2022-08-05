package com.rikkatheworld.wan_android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.rikkatheworld.wan_android.data.PlayDestinations
import com.rikkatheworld.wan_android.data.PlayDestinations.ARTICLE_ROUTE_URL
import com.rikkatheworld.wan_android.data.bean.ArticleModel
import com.rikkatheworld.wan_android.ui.main.MainPage
import java.net.URLEncoder

@Composable
fun NavGraph(
    startDestination: String = PlayDestinations.HOME_PAGE_ROUTE
) {
    val navController = rememberNavController()

    val actions = remember(navController) { PlayActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(PlayDestinations.HOME_PAGE_ROUTE) {
            MainPage(actions)
        }
        composable(
            "${PlayDestinations.ARTICLE_ROUTE}/{$ARTICLE_ROUTE_URL}",
            arguments = listOf(navArgument(ARTICLE_ROUTE_URL) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val parcelable = arguments.getString(ARTICLE_ROUTE_URL)
            val fromJson = Gson().fromJson(parcelable, ArticleModel::class.java)
            ArticlePage(
                article = fromJson,
                onBack = actions.upPress
            )
        }
    }
}

class PlayActions(navController: NavHostController) {

    val enterArticle: (ArticleModel) -> Unit = { article ->
        val gson = Gson().toJson(article).trim()
        val result = URLEncoder.encode(gson, "utf-8")
        navController.navigate("${PlayDestinations.ARTICLE_ROUTE}/$result")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}