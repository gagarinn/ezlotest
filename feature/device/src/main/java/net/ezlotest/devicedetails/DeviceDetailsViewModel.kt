package net.ezlotest.devicedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.ezlotest.device.events.DeviceEvent
import net.ezlotest.domain.models.Device
import net.ezlotest.domain.usecases.GetFakeProfileUseCase
import net.ezlotest.domain.usecases.UpdateDeviceUseCase
import net.ezlotest.ui.Constants
import net.ezlotest.ui.SingleEvent
import net.ezlotest.ui.utils.DeviceIconResolver.getIcon
import javax.inject.Inject

@HiltViewModel
class DeviceDetailsViewModel @Inject constructor(
    private val getFakeProfileUseCase: GetFakeProfileUseCase,
    private val updateDeviceUseCase: UpdateDeviceUseCase
) : ViewModel() {

    private val _profileName = MutableStateFlow("")
    val profileName get() = _profileName.asStateFlow()

    private val _profileAvatar = MutableStateFlow("")
    val profileAvatar get() = _profileAvatar.asStateFlow()

    private val _deviceTitle = MutableStateFlow("")
    val deviceTitle get() = _deviceTitle.asStateFlow()

    private val _icon = MutableStateFlow(0)
    val icon get() = _icon.asStateFlow()

    private val _serialNumber = MutableStateFlow("")
    val serialNumber get() = _serialNumber.asStateFlow()

    private val _macAddress = MutableStateFlow("")
    val macAddress get() = _macAddress.asStateFlow()

    private val _firmware = MutableStateFlow("")
    val firmware get() = _firmware.asStateFlow()

    private val _model = MutableStateFlow("")
    val model get() = _model.asStateFlow()

    private val _inEditMode = MutableStateFlow(false)
    val inEditMode get() = _inEditMode.asStateFlow()

    private val _isChanged = MutableStateFlow(false)
    val isChanged get() = _isChanged.asStateFlow()

    private val _deviceDetailsAction = MutableSharedFlow<SingleEvent>(extraBufferCapacity = 1)
    val deviceDetailsAction = _deviceDetailsAction.asSharedFlow()

    val screenTitle = Constants.DEVICE_DETAILS_SCREEN_NAME
    private var originalTitle = ""
    private var currentDevice: Device? = null

    fun update(isInEditMode: Boolean, device: Device?) {

        currentDevice = device
        _inEditMode.update { isInEditMode }
        getFakeProfileUseCase.invoke().apply {
            _profileName.update { this.name }
            _profileAvatar.update { this.avatarUrl }
        }

        device?.let { device ->
            originalTitle = device.title
            _deviceTitle.update { device.title }
            _icon.update { device.getIcon() }
            _serialNumber.update { device.pkDevice }
            _macAddress.update { device.macAddress }
            _firmware.update { device.firmware }
            _model.update { device.platform }
        }
    }

    fun onSaveClicked() {
        currentDevice?.let {
            viewModelScope.launch {
                updateDeviceUseCase.invoke(it.copy(title = _deviceTitle.value))
                _deviceDetailsAction.tryEmit(DeviceEvent.OnDeviceUpdated)
            }
        }
    }

    fun onTextChanged(text: CharSequence) {
        _deviceTitle.update { text.toString() }
        _isChanged.update { _deviceTitle.value != currentDevice?.title }
    }

    fun onNavigateUpClick(){
        _deviceDetailsAction.tryEmit(DeviceEvent.OnNavigateUp)
    }
}