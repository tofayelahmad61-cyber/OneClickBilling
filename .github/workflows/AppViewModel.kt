
package com.oneclickbilling.app.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import com.oneclickbilling.app.model.*
import kotlin.random.Random

class AppViewModel private constructor() {
    private val _customers = MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>> = _customers

    private val _invoices = MutableStateFlow<List<Invoice>>(emptyList())
    val invoices: StateFlow<List<Invoice>> = _invoices

    private val _stats = MutableStateFlow(Stats())
    val stats: StateFlow<Stats> = _stats

    private var nextCustomerId = 1L
    private var nextInvoiceId = 1L

    fun addCustomer(name: String, phone: String, monthlyFee: Int) {
        val c = Customer(nextCustomerId++, name, phone, monthlyFee, true)
        _customers.value = _customers.value + c
        recompute()
    }

    fun generateMonthlyInvoices() {
        val month = LocalDate.now().let { "${it.year}-${it.monthValue.toString().padStart(2,'0')}" }
        val newOnes = _customers.value.map { c ->
            Invoice(nextInvoiceId++, c.id, c.name, month, c.monthlyFee, 0)
        }
        _invoices.value = _invoices.value + newOnes
        recompute()
    }

    fun addPayment(invoiceId: Long, amount: Int) {
        _invoices.value = _invoices.value.map {
            if (it.id == invoiceId) it.copy(paid = (it.paid + amount).coerceAtMost(it.amount)) else it
        }
        recompute()
    }

    private fun recompute() {
        val totalCustomers = _customers.value.size
        val totalInvoices = _invoices.value.size
        val totalPaid = _invoices.value.sumOf { it.paid }
        val totalAmount = _invoices.value.sumOf { it.amount }
        val totalDue = (totalAmount - totalPaid).coerceAtLeast(0)
        _stats.value = Stats(totalCustomers, totalInvoices, totalPaid, totalDue)
    }

    companion object {
        val instance: AppViewModel by lazy { AppViewModel() }
    }
}
