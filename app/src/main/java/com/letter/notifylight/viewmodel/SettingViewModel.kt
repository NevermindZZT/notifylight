package com.letter.notifylight.viewmodel

import android.app.Application
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.letter.notifylight.LetterApplication
import com.letter.notifylight.model.NotifyShape
import com.letter.utils.FileUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset

private const val TAG = "SettingViewModel"

class SettingViewModel(application: Application) : AndroidViewModel(application) {

    val notifyShapes by lazy {
        MutableLiveData(mutableListOf<NotifyShape>())
    }

    init {
        loadShapes()
    }

    fun moveDefaultShapes() {
        getApplication<LetterApplication>().resources.assets.list("shapes")?.forEach {
            try {
                val inputStream = getApplication<Application>().assets.open("shapes/$it")
                val index = it.lastIndexOf("/")
                val fos = FileOutputStream(
                    File(getApplication<Application>().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                        it.substring(index + 1)))
                fos.write(inputStream.readBytes())
                fos.flush()
                inputStream.close()
                fos.close()
            } catch (e: Exception) {
                Log.e(TAG, "", e)
            }
        }
    }

    fun loadShapes() {
        val dir = getApplication<Application>().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        FileUtils.createOrExistDir(dir)
        try {
            if (dir?.listFiles()?.isEmpty() != false) {
                moveDefaultShapes()
            }
        } catch (e: Exception) {
            Log.e(TAG, "", e)
        }

        notifyShapes.value?.clear()
        val gson = Gson()
        dir?.listFiles()?.forEach {
            try {
                val fis = FileInputStream(it)
                val shape = gson.fromJson(
                    fis.readBytes().toString(Charset.defaultCharset()),
                    NotifyShape::class.java)
                notifyShapes.value?.add(shape)
            } catch (e: Exception) {
                Log.e(TAG, "", e)
            }
        }
    }
}