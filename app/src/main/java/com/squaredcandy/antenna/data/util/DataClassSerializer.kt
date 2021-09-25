package com.squaredcandy.antenna.data.util

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import logcat.asLog
import logcat.logcat

internal class DataClassSerializer<T>(
    private val serializer: KSerializer<T>,
    override val defaultValue: T,
    private val json: Json,
) : Serializer<T> {
    override suspend fun readFrom(input: InputStream): T {
        val result = runCatching {
            val reader = input.bufferedReader()
            try {
                val inputText = reader.readText()
                json.decodeFromString(serializer, inputText)
            } catch (t: Throwable) {
                logcat { t.asLog() }
                null
            } finally {
                reader.close()
            }
        }

        val config = result.getOrNull()
        return config ?: defaultValue
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        runCatching {
            val writer = output.bufferedWriter()
            try {
                val outputText = json.encodeToString(serializer, t)
                writer.write(outputText)
                writer.flush()
            } catch (t: Throwable) {
                logcat { "Failed to write to file - ${t.asLog()}" }
            } finally {
                writer.close()
            }
        }
    }
}