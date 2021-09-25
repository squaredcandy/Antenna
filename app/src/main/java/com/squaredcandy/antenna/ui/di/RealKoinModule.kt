package com.squaredcandy.antenna.ui.di

import android.content.Context
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import com.squaredcandy.antenna.data.device.DeviceFlowDataSource
import com.squaredcandy.antenna.data.device.RealDeviceRepository
import com.squaredcandy.antenna.data.util.DataClassSerializer
import com.squaredcandy.antenna.domain.device.CoroutineTools
import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.domain.util.DeviceRepository
import com.squaredcandy.antenna.ui.device_list.DeviceListScreenModel
import com.squaredcandy.antenna.ui.edit_device.EditDeviceScreenModel
import java.io.File
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.koin.dsl.module

object RealKoinModule {

    fun allModules(appCoroutineScope: CoroutineScope) = listOf(
        coroutineToolsModule(appCoroutineScope),
        jsonModule(),
        FlowDataSource.deviceModule(),
        DataStore.deviceModule(),
        Repository.deviceModule(),
        ScreenModel.deviceListModule(),
        ScreenModel.editDeviceModule(),
    )

    private fun jsonModule() = module {
        single<Json> {
            Json
        }
    }

    private fun coroutineToolsModule(appCoroutineScope: CoroutineScope) = module {
        single<CoroutineTools> {
            object : CoroutineTools {
                override val main: CoroutineDispatcher = Dispatchers.Main
                override val default: CoroutineDispatcher = Dispatchers.Default
                override val io: CoroutineDispatcher = Dispatchers.IO
                override val sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(500L)
                override val appCoroutineScope: CoroutineScope = appCoroutineScope
            }
        }
    }

    private object FlowDataSource {
        fun deviceModule() = module {
            single {
                DeviceFlowDataSource(
                    get(),
                    get()
                )
            }
        }
    }

    private object DataStore {
        fun deviceModule() = module {
            single {
                DataStoreFactory.create(
                    serializer = DataClassSerializer(
                        ListSerializer(Device.serializer()),
                        listOf(),
                        get()
                    ),
                    corruptionHandler = ReplaceFileCorruptionHandler {
                        listOf()
                    },
                    produceFile = {
                        File(get<Context>().filesDir, "datastore/device_list")
                    }
                )
            }
        }
    }

    private object Repository {
        fun deviceModule() = module {
            factory<DeviceRepository> {
                RealDeviceRepository(
                    get()
                )
            }
        }
    }

    private object ScreenModel {
        fun deviceListModule() = module {
            factory {
                DeviceListScreenModel(
                    get(),
                    get(),
                )
            }
        }

        fun editDeviceModule() = module {
            factory { (device: Device?) ->
                EditDeviceScreenModel(
                    device,
                    get(),
                    get(),
                )
            }
        }
    }
}