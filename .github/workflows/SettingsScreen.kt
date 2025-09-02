
package com.oneclickbilling.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("সেটিংস") }) }) { p ->
        Column(Modifier.padding(p).padding(16.dp)) {
            Text("ভবিষ্যতে: ব্যাকআপ, এক্সপোর্ট, থিম ইত্যাদি")
        }
    }
}
