package com.miu.myapp_lesson4.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item(
    @field:StringRes
    @param:StringRes
    val title: Int, //it should take only from string.xml
    @field:DrawableRes
    @param:DrawableRes
    val image: Int
)
