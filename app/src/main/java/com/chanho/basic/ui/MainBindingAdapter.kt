package com.chanho.basic.ui

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

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
