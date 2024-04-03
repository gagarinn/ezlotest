package net.ezlotest.devicedetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.ezlotest.domain.models.Device
import net.ezlotest.domain.usecases.GetFakeProfileUseCase
import net.ezlotest.ui.Constants
import net.ezlotest.ui.utils.DeviceIconResolver.getIcon
import javax.inject.Inject

@HiltViewModel
class DeviceDetailsViewModel @Inject constructor(
    private val getFakeProfileUseCase: GetFakeProfileUseCase
): ViewModel() {

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

    val screenTitle = Constants.DEVICE_DETAILS_SCREEN_NAME

    fun update(device: Device?) {
        getFakeProfileUseCase.invoke().apply {
            _profileName.update { this.name }
            _profileAvatar.update { this.avatarUrl }
        }

        device?.let { device ->
            _deviceTitle.update { device.title}
            _icon.update { device.getIcon() }
            _serialNumber.update { device.pkDevice }
            _macAddress.update { device.macAddress }
            _firmware.update { device.firmware }
            _model.update { device.platform }
        }
    }
}