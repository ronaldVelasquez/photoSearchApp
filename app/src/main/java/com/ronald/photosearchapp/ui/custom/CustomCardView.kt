package com.ronald.photosearchapp.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import com.bumptech.glide.Glide
import com.ronald.photosearchapp.R
import com.ronald.photosearchapp.domain.Photo


class CustomCardView(context: Context?) :
    BaseCardView(context, null, 0) {
    init {
        LayoutInflater.from(getContext()).inflate(R.layout.item_card_view, this)
        isFocusable = true
        isFocusableInTouchMode = true
    }

    fun updateCustom(photo: Photo) {
        val name = findViewById<TextView>(R.id.txt_image_name)
        val detail = findViewById<TextView>(R.id.txt_image_date_author)
        val imageView = findViewById<ImageView>(R.id.img_main)
        name.text = photo.title
        detail.text = "${photo.author} / ${photo.publish}"
        if (photo.url.isNullOrEmpty()) {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this.context,
                    R.drawable.placeholder
                )
            )
        } else {
            Glide.with(imageView)
                .load(photo.url)
                .placeholder(R.drawable.placeholder_loading)
                .centerCrop()
                .into(imageView)
        }

    }
}