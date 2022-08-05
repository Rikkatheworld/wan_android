package com.rikkatheworld.wan_android

class BannerBean {
    companion object {
        private const val TAG = "BannerBean"
    }
}

data class BannerModel(
    val `data`: List<Data?>?,
    val errorCode: Int?,
    val errorMsg: String?
)

data class Data(
    val desc: String?,
    val id: Int?,
    val imagePath: String?,
    val isVisible: Int?,
    val order: Int?,
    val title: String?,
    val type: Int?,
    val url: String?
)