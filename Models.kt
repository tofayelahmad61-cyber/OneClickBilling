
package com.oneclickbilling.app.model

data class Customer(
    val id: Long,
    val name: String,
    val phone: String,
    val monthlyFee: Int,
    val active: Boolean = true
)

data class Invoice(
    val id: Long,
    val customerId: Long,
    val customerName: String,
    val month: String, // e.g., 2025-09
    val amount: Int,
    val paid: Int = 0
)

data class Stats(
    val totalCustomers: Int = 0,
    val totalInvoices: Int = 0,
    val totalPaid: Int = 0,
    val totalDue: Int = 0
)
