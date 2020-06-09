package com.letter.notifylight.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.letter.notifylight.model.NotifyShape
import com.letter.notifylight.repository.LoadRepo
import java.io.File
import java.nio.charset.Charset

private const val TAG = "NotifyLightViewModel"

class NotifyLightViewModel(application: Application) : AndroidViewModel(application) {

    var notifyShape = MutableLiveData<NotifyShape>()

    private val loadRepo by lazy {
        LoadRepo()
    }

    init {
        loadShape()
    }

    fun loadShape() {
        notifyShape.value = loadRepo.loadCurrentShape(getApplication())
    }
}