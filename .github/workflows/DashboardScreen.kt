
package com.oneclickbilling.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oneclickbilling.app.vm.AppViewModel

@Composable
fun DashboardScreen(nav: NavController, vm: AppViewModel = AppViewModel.instance) {
    val stats = vm.stats.collectAsState()
    Scaffold(
        topBar = { TopAppBar(title = { Text("OneClickBilling") }) }
    ) { p ->
        Column(Modifier.padding(p).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("ড্যাশবোর্ড", style = MaterialTheme.typography.titleLarge)
            Text("মোট গ্রাহক: ${stats.value.totalCustomers}")
            Text("মোট ইনভয়েস: ${stats.value.totalInvoices}")
            Text("মোট আদায়: ৳${stats.value.totalPaid}")
            Text("মোট বকেয়া: ৳${stats.value.totalDue}")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { nav.navigate("customers") }) { Text("গ্রাহক") }
                Button(onClick = { nav.navigate("invoices") }) { Text("ইনভয়েস") }
                Button(onClick = { nav.navigate("payments") }) { Text("পেমেন্ট") }
                Button(onClick = { nav.navigate("settings") }) { Text("সেটিংস") }
            }
            Divider()
            Button(
                onClick = { vm.generateMonthlyInvoices() },
            ) { Text("এক ক্লিকে মাসিক বিল তৈরি") }
        }
    }
}
