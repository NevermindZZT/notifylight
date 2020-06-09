package com.letter.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager


class ScreenUtils {
    companion object {
        @JvmStatic
        fun getWidth(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            val point = Point()
            windowManager?.defaultDisplay?.getSize(point)
            return point.x
        }

        @JvmStatic
        fun getHeight(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            val point = Point()
            windowManager?.defaultDisplay?.getSize(point)
            return point.y
        }
    }
}