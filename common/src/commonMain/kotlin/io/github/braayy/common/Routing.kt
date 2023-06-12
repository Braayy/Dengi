package io.github.braayy.common

import io.github.braayy.common.model.Database

sealed interface Route

object HomeRoute : Route

data class DatabaseRoute(val database: Database) : Route