package io.github.braayy.common.model

import java.math.BigDecimal

data class Account(
    val name: String,
    val type: AccountType,
    val group: String,
    val openingBalance: BigDecimal,
    val notes: String,
)

enum class AccountType {
    Asset,
    Liability,
}