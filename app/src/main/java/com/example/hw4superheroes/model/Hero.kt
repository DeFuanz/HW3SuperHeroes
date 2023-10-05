package com.example.hw4superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val vulnerability: Int,
    @StringRes val vulnerabilityDetail: Int
)
