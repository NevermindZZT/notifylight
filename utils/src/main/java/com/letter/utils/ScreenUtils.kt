package com.letter.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager


class ScreenUtils {
    companion object {
        @JvmStatic
        fun getWidth(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            val displayMetrics = DisplayMetrics()
            windowManager?.defaultDisplay?.getRealMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }

        @JvmStatic
        fun getHeight(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            val displayMetrics = DisplayMetrics()
            windowManager?.defaultDisplay?.getRealMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }
    }
}