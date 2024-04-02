package net.ezlotest.device

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.ezlotest.domain.usecases.FetchDevicesUseCase
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val fetchDevicesUseCase: FetchDevicesUseCase
): ViewModel() {

    fun fetch() {
        viewModelScope.launch {
            fetchDevicesUseCase.invoke()
        }
    }
}
