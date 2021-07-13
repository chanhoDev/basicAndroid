package com.chanho.basic.ui.home

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chanho.basic.R

@BindingAdapter("type", "htmlText")
fun setHtmlText(tv: TextView, type: String?, text: String) {
    var title: String
    when (type) {
        "pubdate" -> {
            title = "출시일:"
        }
        "rating" -> {
            title = "평점:"
        }
        "director" -> {
            title = "감독:"
        }
        "actor" -> {
            title = "배우:"
        }
        else -> {
            title = ""
        }
    }
    if (text.trim().isNullOrEmpty()) {
        tv.visibility = View.GONE
    } else {
        tv.visibility = View.VISIBLE
        tv.text = "$title" + HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(iv: ImageView, url: String?) {
    url?.let {
        Glide.with(iv.context)
            .load(url)
            .override(50)
            .transform(CenterCrop(),RoundedCorners(5))
            .error(R.drawable.outline_account_box_white_48)
            .into(iv)
    }
}