package net.ezlotest.device.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.ezlotest.device.DeviceItem
import net.ezlotest.device.DeviceItemType
import net.ezlotest.device.events.DeviceEvent
import net.ezlotest.domain.entities.Device
import net.ezlotest.domain.result.NetworkStatus
import net.ezlotest.domain.usecases.FetchDevicesUseCase
import net.ezlotest.domain.usecases.GetDevicesUseCase
import net.ezlotest.domain.usecases.GetFakeProfileUseCase
import net.ezlotest.ui.Constants
import net.ezlotest.ui.SingleEvent
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val fetchDevicesUseCase: FetchDevicesUseCase,
    private val getDevicesUseCase: GetDevicesUseCase,
    getFakeProfileUseCase: GetFakeProfileUseCase
) : ViewModel() {

    val profile = getFakeProfileUseCase.invoke()
    val screenTitle = Constants.DEVICES_SCREEN_NAME

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    private val _hasItems = MutableStateFlow(false)
    val hasItems get() = _hasItems.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError get() = _hasError.asStateFlow()

    private val _devicesAction = MutableSharedFlow<SingleEvent>(extraBufferCapacity = 1)
    val devicesAction = _devicesAction.asSharedFlow()

    fun getDevices() {
        viewModelScope.launch {
            getDevicesUseCase.invoke().collect { result ->
                handleResult(result)
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            fetchDevicesUseCase.invoke().collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: NetworkStatus<List<Device>>) {
        when (result) {
            is NetworkStatus.Loading -> {
                _isLoading.update { true }
                _hasError.update { false }
            }

            is NetworkStatus.Error -> {
                _isLoading.update { false }
                _hasError.update { true }
            }

            is NetworkStatus.Success -> {
                _isLoading.update { false }
                _hasError.update { false }
                _hasItems.update { result.data.orEmpty().isNotEmpty() }
                updateDevices(result.data.orEmpty())
            }
        }
    }

    private fun updateDevices(devices: List<Device>) {
        val deviceItems = mutableListOf<DeviceItem>()
        deviceItems.add(DeviceItem(type = DeviceItemType.PROFILE, profile = profile))
        deviceItems.addAll(devices.map { DeviceItem(type = DeviceItemType.DEVICE, device = it) })
        _devicesAction.tryEmit(DeviceEvent.OnShowDevices(deviceItems))
    }
}
