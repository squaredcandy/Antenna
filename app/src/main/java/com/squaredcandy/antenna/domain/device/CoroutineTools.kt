package com.squaredcandy.antenna.domain.device

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted

interface CoroutineTools {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val sharingStarted: SharingStarted
    val appCoroutineScope: CoroutineScope
}