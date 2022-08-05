package com.rikkatheworld.wan_android

sealed class PlayState

object PlayLoading: PlayState()
data class PlaySuccess<T>(val data: T): PlayState()
data class PlayError(val e: Throwable): PlayState()
