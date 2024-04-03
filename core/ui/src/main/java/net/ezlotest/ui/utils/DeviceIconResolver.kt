package net.ezlotest.ui.utils

import net.ezlotest.domain.models.Device
import net.ezlotest.ui.R

private const val SERCOMM_G450 = "Sercomm G450"
private const val SERCOMM_G550 = "Sercomm G550"
private const val MICASAVERDE_VERALITE = "MiCasaVerde VeraLite"
private const val SERCOMM_NA900 = "Sercomm NA900"
private const val SERCOMM_NA301 = "Sercomm NA301"
private const val SERCOMM_NA930 = "Sercomm NA930"

object DeviceIconResolver {

    fun Device.getIcon() : Int {
        return when(platform){
            SERCOMM_G450 -> R.drawable.vera_plus_big
            SERCOMM_G550 -> R.drawable.vera_secure_big
            MICASAVERDE_VERALITE -> R.drawable.vera_edge_big
            SERCOMM_NA900 -> R.drawable.vera_edge_big
            SERCOMM_NA301 -> R.drawable.vera_edge_big
            SERCOMM_NA930 -> R.drawable.vera_edge_big
            else -> R.drawable.vera_edge_big
        }
    }
}