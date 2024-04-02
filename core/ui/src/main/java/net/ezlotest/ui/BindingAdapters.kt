package net.ezlotest.ui

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import net.ezlotest.ui.glide.GlideApp

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:isVisible")
    fun View.setVisibility(value: Boolean?) {
        isVisible = value ?: false
    }

    @JvmStatic
    @BindingAdapter("android:avatarRound")
    fun ImageView.setAvatarRound(imageUrl: String?) {
        if (imageUrl.isNullOrEmpty().not()) {
            GlideApp
                .with(this)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.ic_avatar_placeholder)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("android:srcRoundedCorners")
    fun ImageView.setImgRoundedCorners(@DrawableRes imageRes: Int) {
        GlideApp
            .with(this)
            .load(imageRes)
            .transform(
                CenterInside(),
                RoundedCorners(context.resources.getDimensionPixelSize(R.dimen.item_image_corner_size))
            )
            .into(this)
    }
}