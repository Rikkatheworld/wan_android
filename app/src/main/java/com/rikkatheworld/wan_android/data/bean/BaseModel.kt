package com.rikkatheworld.wan_android.data.bean

data class BaseModel<T>(
    val data: T,
    val errorCode: Int?,
    val errorMsg: String
)