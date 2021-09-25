package com.squaredcandy.antenna.domain.device

import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val id: Long = Random.nextLong(),
    val name: String = "",
    val macAddress: String = "",
    val ipAddress: String = "",
    val port: DevicePort = DevicePort.NINE,
)