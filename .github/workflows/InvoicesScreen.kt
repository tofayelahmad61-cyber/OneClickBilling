
package com.oneclickbilling.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oneclickbilling.app.vm.AppViewModel
import com.oneclickbilling.app.model.Invoice

@Composable
fun InvoicesScreen(nav: NavController, vm: AppViewModel = AppViewModel.instance) {
    val invoices = vm.invoices.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("ইনভয়েস") }) }) { p ->
        Column(Modifier.padding(p).padding(16.dp)) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(invoices.value) { inv: Invoice ->
                    ListItem(
                        headlineContent = { Text("ইনভ #${inv.id} • ${inv.customerName}") },
                        supportingContent = { Text("মাস: ${inv.month} • মোট: ৳${inv.amount} • পরিশোধ: ৳${inv.paid}") },
                        trailingContent = { Text(if (inv.amount > inv.paid) "Due" else "Paid") }
                    )
                    Divider()
                }
            }
        }
    }
}
