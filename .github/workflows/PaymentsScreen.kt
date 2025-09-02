
package com.oneclickbilling.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oneclickbilling.app.vm.AppViewModel

@Composable
fun PaymentsScreen(nav: NavController, vm: AppViewModel = AppViewModel.instance) {
    var invoiceId by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("পেমেন্ট") }) }) { p ->
        Column(Modifier.padding(p).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(invoiceId, { invoiceId = it }, label = { Text("ইনভয়েস আইডি") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(amount, { amount = it }, label = { Text("টাকার পরিমাণ") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                val id = invoiceId.toLongOrNull()
                val amt = amount.toIntOrNull()
                if (id != null && amt != null) {
                    vm.addPayment(id, amt)
                    invoiceId = ""; amount = ""
                }
            }) { Text("পেমেন্ট যুক্ত করুন") }
        }
    }
}
