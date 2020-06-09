package com.letter.notifylight.view

import android.content.Context
import android.content.dp2px
import android.content.px2dp
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.databinding.InverseBindingAdapter
import com.letter.notifylight.R
import com.letter.notifylight.databinding.LayoutSeekBarBinding

class SeekBar @JvmOverloads
constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutSeekBarBinding.inflate(
        LayoutInflater.from(context),
        this,
        false)

    var progress: Int
    set(value) {
        binding.seekBar.progress = value
    }
    get() = binding.seekBar.progress

    var max: Int
    set(value) {
        binding.seekBar.max = value
    }
    get() = binding.seekBar.max

    init {
        addView(binding.root)

        val attrArray = context.obtainStyledAttributes(attrs, R.styleable.SeekBar)
        val text = attrArray.getString(R.styleable.SeekBar_android_text)
        val textSize = attrArray.getDimension(R.styleable.SeekBar_android_textSize, context.dp2px(14))
        val textColor = attrArray.getColor(R.styleable.SeekBar_android_textColor, Color.BLACK)

        val max = attrArray.getInt(R.styleable.SeekBar_android_max, 100)
        val progress = attrArray.getInt(R.styleable.SeekBar_android_progress, 0)
        attrArray.recycle()

        binding.titleText.apply {
            this.text = text
            this.textSize = context.px2dp(textSize)
            setTextColor(textColor)
        }
        binding.valueText.apply {
            this.textSize = context.px2dp(textSize)
            setTextColor(textColor)
        }
        binding.seekBar.apply {
            this.max = max
            this.progress = progress
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    binding.valueText.text = "$progress"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

                override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
            })
        }
        binding.minusButton.setOnClickListener {
            binding.seekBar.progress -= 1
        }
        binding.plusButton.setOnClickListener {
            binding.seekBar.progress += 1
        }
    }
}