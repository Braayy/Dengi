package io.github.braayy.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.braayy.common.model.Account
import io.github.braayy.common.model.Database
import io.github.braayy.common.model.AccountType
import io.github.braayy.common.screen.Database
import io.github.braayy.common.screen.DatabaseScreen
import io.github.braayy.common.screen.HomeScreen
import java.math.BigDecimal

val TEST_DATA = listOf(
    Database(
        name = "Pessoal",
        accounts = listOf(
            Account(
                name = "N26 Principal",
                type = AccountType.Asset,
                group = "Ativos - N26",
                openingBalance = BigDecimal.ZERO,
                notes = "",
            ),
            Account(
                name = "N26 Almoço",
                type = AccountType.Asset,
                group = "Ativos - N26",
                openingBalance = BigDecimal.ZERO,
                notes = "",
            ),
        ),
    ),
)

@Composable
fun App() {
    var currentRoute by remember { mutableStateOf<Route>(DatabaseRoute(TEST_DATA[0])) }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val route = currentRoute) {
            is HomeRoute -> HomeScreen(
                databases = TEST_DATA,
                onClickDatabase = { currentRoute = DatabaseRoute(it) },
                onCreateDatabase = { _, _ -> },
                onDeleteDatabase = {}
            )

            is DatabaseRoute -> DatabaseScreen(
                database = route.database,
            )
        }
    }
}

