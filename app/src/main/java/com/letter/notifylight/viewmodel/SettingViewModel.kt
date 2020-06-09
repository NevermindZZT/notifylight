package com.letter.notifylight.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.*
import com.letter.notifylight.model.NotifyShape
import com.letter.notifylight.repository.LoadRepo

private const val TAG = "SettingViewModel"

class SettingViewModel(application: Application) : AndroidViewModel(application) {

    val components: ObservableList<NotifyShape.NotifyComponent> = ObservableArrayList()
    val currentShape = MutableLiveData<NotifyShape>()
    val notifyShapes by lazy {
        MutableLiveData(mutableListOf<NotifyShape>())
    }
    private val loadRepo by lazy {
        LoadRepo()
    }


    init {
        loadShapes()
        loadCurrentShape()
    }

    fun loadShapes() {
        notifyShapes.value = loadRepo.loadShapes(getApplication())
    }

    fun loadCurrentShape() {
        currentShape.value = loadRepo.loadCurrentShape(getApplication())
        components.clear()
        if (currentShape.value?.components?.isNotEmpty() == true) {
            components.addAll(currentShape.value?.components !!)
            Log.d(TAG, "not empty")
        }
    }
}