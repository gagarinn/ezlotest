package net.ezlotest.devicedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.ezlotest.device.R
import net.ezlotest.device.databinding.FragmentDeviceDetailsBinding
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

        viewModel.update(device)
    }
}