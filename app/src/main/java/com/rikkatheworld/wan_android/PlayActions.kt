package com.rikkatheworld.wan_android

import androidx.navigation.NavHostController
import com.google.gson.Gson
import java.net.URLEncoder

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