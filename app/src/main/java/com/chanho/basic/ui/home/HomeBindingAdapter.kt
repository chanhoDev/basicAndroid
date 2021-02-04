package com.chanho.basic.ui.home

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.chanho.basic.R

@BindingAdapter("type", "remainStates")
fun setRemainCount(tv: TextView, type: Boolean, remainState: String?) {
    var remainStateText = "충분"
    var count = "100개 이상"
    var color = Color.GREEN
    remainState?.let {
        when (remainState) {
            "plenty" -> {
                remainStateText = "충분"
                count = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStateText = "여유"
                count = "30개 이상"
                color = Color.YELLOW
            }
            "few" -> {
                remainStateText = "매진 임박"
                count = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStateText = "재고 없음"
                count = "1개 이하"
                color = Color.GRAY
            }
        }
        if (type) {
            tv.text = (remainStateText)
            tv.setTextColor(color)
        } else {
            tv.text = (count)
            tv.setTextColor(color)
        }
    }
}

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
            .override(100,150)
            .centerCrop()
            .placeholder(R.drawable.ic_image_null)
            .error(R.drawable.ic_image_null)
            .into(iv)
    }
}