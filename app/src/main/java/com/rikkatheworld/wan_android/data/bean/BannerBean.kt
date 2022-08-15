package com.rikkatheworld.wan_android.data.bean

import com.rikkatheworld.wan_android.widget.banner.BaseBannerBean

data class BannerBean(
    val desc: String?,
    val id: Int?,
    val imagePath: String?,
    val isVisible: Int?,
    val order: Int?,
    val title: String?,
    val type: Int?,
    val url: String?
) : BaseBannerBean() {
    override val data: Any?
        get() = this
}