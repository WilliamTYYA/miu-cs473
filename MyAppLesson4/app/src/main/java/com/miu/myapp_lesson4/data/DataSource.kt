package com.miu.myapp_lesson4.data

import com.miu.myapp_lesson4.R
import com.miu.myapp_lesson4.model.Item

object DataSource {
    fun loadData(): List<Item> {
        return listOf(
            Item(
                title = R.string.miu_campus,
                image = R.drawable.miu_campus
            ),
            Item(
                title = R.string.rainbow,
                image = R.drawable.rainbow
            ),
            Item(
                title = R.string.sustainable_living_center,
                image = R.drawable.sustainable_living_center
            ),
            Item(
                title = R.string.compro_admission_team,
                image = R.drawable.compro_admission_team
            ),
            Item(
                title = R.string.faculty_student,
                image = R.drawable.faculty_student
            ),
            Item(
                title = R.string.friends,
                image = R.drawable.friends
            )
        )
    }
}