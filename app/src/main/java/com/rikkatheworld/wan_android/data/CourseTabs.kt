package com.rikkatheworld.wan_android.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rikkatheworld.wan_android.R

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    HOME_PAGE(R.string.home_page, R.drawable.ic_nav_news_normal),
    PROJECT(R.string.project, R.drawable.ic_nav_tweet_normal),
    OFFICIAL_ACCOUNT(R.string.official_account, R.drawable.ic_nav_discover_normal),
    MINE(R.string.mine, R.drawable.ic_nav_my_normal)
}