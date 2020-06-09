package com.letter.presenter

import android.view.View

interface ItemViewPresenter {
    fun onItemViewClick(adapter: Any, position: Int, view: View)
}