package com.miu.navigationapp.feature.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.miu.navigationapp.data.Setting

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,

) {
    Column {
        Setting.entries.toList().forEach { setting: Setting ->
            Text(text = setting.name)
        }
    }
}