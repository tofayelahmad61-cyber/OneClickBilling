
package com.oneclickbilling.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oneclickbilling.app.vm.AppViewModel
import com.oneclickbilling.app.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen(nav: NavController, vm: AppViewModel = AppViewModel.instance) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var plan by remember { mutableStateOf(500) }

    Scaffold(topBar = { TopAppBar(title = { Text("গ্রাহক ব্যবস্থাপনা") }) }) { p ->
        Column(Modifier.padding(p).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(name, { name = it }, label = { Text("নাম") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(phone, { phone = it }, label = { Text("মোবাইল") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(plan.toString(), {
                plan = it.toIntOrNull() ?: 0
            }, label = { Text("মাসিক চার্জ (৳)") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                if (name.isNotBlank()) vm.addCustomer(name, phone, plan)
                name = ""; phone = ""; plan = 500
            }) { Text("গ্রাহক যোগ করুন") }

            Divider()
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(vm.customers.collectAsState().value) { c: Customer ->
                    ListItem(
                        headlineContent = { Text(c.name) },
                        supportingContent = { Text("৳${c.monthlyFee} • ${c.phone}") },
                        trailingContent = { Text(if (c.active) "Active" else "Inactive") }
                    )
                }
            }
        }
    }
}
