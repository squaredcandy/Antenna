package com.squaredcandy.antenna.ui.util

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import logcat.logcat

object WakeOnLan {

    fun sendSignal(
        ipAddressString: String,
        macAddressString: String,
        port: Int
    ): Result<Unit> {
        return kotlin.runCatching {
            val macAddressBytes: ByteArray = getMacAddressAsByteArray(macAddressString)
            /**
             * The WOL pack is made from 1 of 6 bytes of 0xff and 16 of [macAddressString]
             */
            var bytes = ByteArray(PADDING) { 0xff.toByte() }
            repeat(16) {
                bytes += macAddressBytes
            }

            val address = InetAddress.getByName(ipAddressString)
            val packet = DatagramPacket(bytes, bytes.size, address, port)
            val socket = DatagramSocket()
            socket.send(packet)
            socket.close()
        }
    }

    private fun getMacAddressAsByteArray(macAddressString: String): ByteArray {
        val hex = macAddressString.split(Regex("([:\\-])")).toTypedArray()
        require(hex.size == PADDING) { "Invalid MAC address." }
        val bytes = ByteArray(PADDING)
        try {
            for (i in 0..5) {
                bytes[i] = hex[i].toInt(16).toByte()
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid hex digit in MAC address.")
        }
        return bytes
    }

    private const val PADDING = 6
}