package com.ronald.photosearchapp.ui

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.ronald.photosearchapp.domain.Photo
import com.ronald.photosearchapp.ui.custom.CustomCardView

class CardPresenter: Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = CustomCardView(parent.context)
        cardView.apply {
//            setMainImageDimensions(500, 250)
            isFocusable = true
            isFocusableInTouchMode = true
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val photo = item as Photo
        val cardView = viewHolder.view as CustomCardView
        cardView.updateCustom(photo)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {

    }
}