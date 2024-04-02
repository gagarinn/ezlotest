package net.ezlotest.device.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import net.ezlotest.device.databinding.ItemDeviceBinding
import net.ezlotest.device.databinding.ItemHeaderBinding

sealed class DevicesViewHolder (val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    class DeviceViewHolder (binding: ItemDeviceBinding) : DevicesViewHolder(binding)
    class HeaderViewHolder (binding: ItemHeaderBinding) : DevicesViewHolder(binding)
}