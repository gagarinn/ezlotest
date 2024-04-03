package net.ezlotest.devicedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.ezlotest.device.R
import net.ezlotest.device.databinding.FragmentDeviceDetailsBinding
import net.ezlotest.device.events.DeviceEvent
import net.ezlotest.domain.models.Device
import net.ezlotest.ui.Constants
import net.ezlotest.ui.utils.parcelable

@AndroidEntryPoint
class DeviceDetailsFragment : Fragment() {

    private val viewModel: DeviceDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDeviceDetailsBinding.bind(requireView())
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        val device = requireArguments().parcelable<Device>(Constants.DEVICE_BUNDLE_KEY)
        val isInEditMode = requireArguments().getBoolean(Constants.DEVICE_EDIT_BUNDLE_KEY)

        viewModel.update(isInEditMode, device)
        setUpViewModelActions()
    }

    private fun setUpViewModelActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deviceDetailsAction.collect { event ->
                when (event) {
                    is DeviceEvent.OnDeviceUpdated,
                    DeviceEvent.OnNavigateUp -> findNavController().navigateUp()
                }
            }
        }
    }
}