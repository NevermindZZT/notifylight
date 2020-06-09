package com.letter.notifylight.utils

import androidx.databinding.InverseBindingAdapter
import com.letter.notifylight.view.SeekBar

@InverseBindingAdapter(attribute = "progress")
fun getProgress(seekBar: SeekBar) = seekBar.progress

