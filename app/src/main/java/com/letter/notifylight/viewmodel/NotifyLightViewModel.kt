package com.letter.notifylight.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.letter.notifylight.model.NotifyShape
import com.letter.notifylight.repository.ShapeRepo

private const val TAG = "NotifyLightViewModel"

class NotifyLightViewModel(application: Application) : AndroidViewModel(application) {

    var notifyShape = MutableLiveData<NotifyShape>()

    private val shapeRepo by lazy {
        ShapeRepo()
    }

    init {
        loadShape()
    }

    fun loadShape() {
        notifyShape.value = shapeRepo.loadCurrentShape(getApplication())
    }
}