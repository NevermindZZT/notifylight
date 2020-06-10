package com.letter.notifylight.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.*
import com.letter.notifylight.model.NotifyShape
import com.letter.notifylight.repository.ShapeRepo

private const val TAG = "SettingViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val components: ObservableList<NotifyShape.NotifyComponent> = ObservableArrayList()
    val currentShape = MutableLiveData<NotifyShape>()
    val notifyShapes by lazy {
        MutableLiveData(mutableListOf<NotifyShape>())
    }
    private val shapeRepo by lazy {
        ShapeRepo()
    }


    init {
        loadShapes()
        loadCurrentShape()
    }

    fun loadShapes() {
        notifyShapes.value = shapeRepo.loadShapes(getApplication())
    }

    fun loadCurrentShape() {
        currentShape.value = shapeRepo.loadCurrentShape(getApplication())
        loadComponents()
    }
    
    fun saveCurrentShape() {
        if (currentShape.value != null) {
            shapeRepo.saveCurrentShape(getApplication(), currentShape.value !!)
        }
    }

    fun setCurrentShape(index: Int) {
        currentShape.value = notifyShapes.value?.get(index)
        loadComponents()
    }

    fun loadComponents() {
        components.clear()
        if (currentShape.value?.components?.isNotEmpty() == true) {
            components.addAll(currentShape.value?.components !!)
            Log.d(TAG, "not empty")
        }
    }

    fun setShapeColor(position: Int, color: Int) {
        currentShape.value?.components?.get(position)?.color = color
    }
}