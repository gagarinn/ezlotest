package net.ezlotest.device.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.ezlotest.device.R
import net.ezlotest.device.adapter.DeviceAdapter
import net.ezlotest.device.databinding.FragmentDeviceListBinding
import net.ezlotest.device.events.DeviceEvent
import net.ezlotest.device.viewmodel.DeviceListViewModel
import net.ezlotest.domain.entities.Device
import net.ezlotest.ui.Constants
import javax.inject.Inject

@AndroidEntryPoint
class DeviceListFragment : Fragment() {

    @Inject
    lateinit var deviceAdapter: DeviceAdapter
    lateinit var binding: FragmentDeviceListBinding
    private val viewModel: DeviceListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_device_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeviceListBinding.bind(requireView())
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
            adapter = deviceAdapter

            devicesRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    devicesRecyclerView.context,
                    LinearLayout.VERTICAL
                )
            )
        }

        setUpViewModelActions()
        setUpAdapterActions()
        updateDevicesWithLifecycleScope()
    }

    private fun setUpAdapterActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            deviceAdapter.callback = { event ->
                when (event) {
                    is DeviceEvent.OnDeviceClicked -> navigateToDetails(event.device)
                }
            }
        }
    }

    private fun navigateToDetails(device: Device?) {
        val bundle = Bundle()
        device?.let {
            bundle.putParcelable(Constants.DEVICE_BUNDLE_KEY, it)
        }
        findNavController().navigate(R.id.action_device_list_to_details, bundle)
    }

    private fun setUpViewModelActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.devicesAction.collect { event ->
                when (event) {
                    is DeviceEvent.OnShowDevices -> {
                        deviceAdapter.update(event.list)
                        binding.deviceListSwipeRefresh.isRefreshing = false
                    }
                }
            }
        }
    }

    private fun updateDevicesWithLifecycleScope() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getDevices()
            }
        }
    }
}