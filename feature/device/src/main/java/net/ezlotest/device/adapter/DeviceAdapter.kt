package net.ezlotest.device.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.ezlotest.device.DeviceItem
import net.ezlotest.device.DeviceItemType
import net.ezlotest.device.databinding.ItemDeviceBinding
import net.ezlotest.device.databinding.ItemHeaderBinding
import net.ezlotest.ui.SingleEvent
import javax.inject.Inject

class DeviceAdapter @Inject constructor() : RecyclerView.Adapter<DevicesViewHolder>() {

    var callback: ((SingleEvent) -> Unit)? = null

    private val items: MutableList<DeviceItem> = mutableListOf()

    fun update(newItems: List<DeviceItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DeviceItemType.PROFILE.ordinal -> DevicesViewHolder.HeaderViewHolder(
                ItemHeaderBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else ->
                DevicesViewHolder.DeviceViewHolder(
                    ItemDeviceBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
        }
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        val item = items[position]
        when (item.type) {
            DeviceItemType.PROFILE -> {
                items[position].profile?.let {
                    val viewModel = HeaderItemViewModel(it)
                    holder.binding.setVariable(net.ezlotest.device.BR.model, viewModel)
                    holder.binding.executePendingBindings()
                }
            }
            else -> {
                val viewModel = DeviceItemViewModel(
                    items[position].device,
                    callback
                )
                holder.binding.setVariable(net.ezlotest.device.BR.model, viewModel)
                holder.binding.executePendingBindings()
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type.ordinal
}
