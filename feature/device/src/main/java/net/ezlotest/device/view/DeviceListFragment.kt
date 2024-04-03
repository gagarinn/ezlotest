package net.ezlotest.device.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
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
import net.ezlotest.domain.models.Device
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
                    is DeviceEvent.OnEditDeviceClicked -> navigateToEditDevice(event.device)
                    is DeviceEvent.OnLongDeviceClicked -> showDeleteDeviceDialog(event.pkDevice)
                }
            }
        }
    }

    private fun navigateToEditDevice(device: Device) {
        val bundle = Bundle().also {
            it.putParcelable(Constants.DEVICE_BUNDLE_KEY, device)
            it.putBoolean(Constants.DEVICE_EDIT_BUNDLE_KEY, true)
        }
        findNavController().navigate(R.id.action_device_list_to_details, bundle)
    }

    private fun showDeleteDeviceDialog(pkDevice: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage(getString(R.string.delete_device_message_dialog))
            .setTitle(getString(R.string.delete_device_title_dialog))
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                viewModel.deleteDevice(pkDevice)
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                Unit
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun navigateToDetails(device: Device) {
        val bundle = Bundle().also {
            it.putParcelable(Constants.DEVICE_BUNDLE_KEY, device)
        }
        findNavController().navigate(R.id.action_device_list_to_details, bundle)
    }

    private fun setUpViewModelActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.devicesAction.collect { event ->
                when (event) {
                    is DeviceEvent.OnShowDevices -> deviceAdapter.update(event.list)
                    DeviceEvent.OnResetClicked -> showResetDialog()
                }
            }
        }
    }

    private fun showResetDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage(getString(R.string.reset_device_message_dialog))
            .setTitle(getString(R.string.reset_device_title_dialog))
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                viewModel.resetDevices()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                Unit
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun updateDevicesWithLifecycleScope() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getDevices()
            }
        }
    }
}