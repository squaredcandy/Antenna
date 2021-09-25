package com.squaredcandy.antenna.domain.util

import kotlinx.coroutines.flow.StateFlow

interface FlowDataSource<T> {
    val stateFlow: StateFlow<T>
    suspend fun updateData(transform: suspend (t: T) -> T)
}