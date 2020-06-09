package com.letter.notifylight.repository

import android.content.Context
import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.letter.notifylight.model.NotifyShape
import com.letter.utils.FileUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset

private const val TAG = "LoadRepo"

class LoadRepo {


    fun moveDefaultShapes(context: Context) {
        context.resources.assets.list("shapes")?.forEach {
            try {
                val inputStream = context.assets.open("shapes/$it")
                val index = it.lastIndexOf("/")
                val fos = FileOutputStream(
                    File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
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

    fun loadShapes(context: Context): MutableList<NotifyShape> {
        val dir =context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        FileUtils.createOrExistDir(dir)
        try {
            if (dir?.listFiles()?.isEmpty() != false) {
                moveDefaultShapes(context)
            }
        } catch (e: Exception) {
            Log.e(TAG, "", e)
        }

        val list = mutableListOf<NotifyShape>()
        val gson = Gson()
        dir?.listFiles()?.forEach {
            try {
                val fis = FileInputStream(it)
                val shape = gson.fromJson(
                    fis.readBytes().toString(Charset.defaultCharset()),
                    NotifyShape::class.java)
                list.add(shape)
            } catch (e: Exception) {
                Log.e(TAG, "", e)
            }
        }
        return list
    }

    fun loadCurrentShape(context: Context): NotifyShape? {
        val shapeFile = File(context.dataDir, "files/config_shape.json")
        Log.d(TAG, "file: $shapeFile")
        if (!shapeFile.exists()) {
            Log.d(TAG, "file not exist")
            try {
                val inputStream = context.assets.open("shapes/default_rect.json")
                val fos = context.openFileOutput("config_shape.json", Context.MODE_PRIVATE)
                fos.write(inputStream.readBytes())
                fos.flush()
                inputStream.close()
                fos.close()
            } catch (e: Exception) {
                Log.e(TAG, "", e)
            }
        }

        val gson = Gson()
        try {
            val fis = context.openFileInput("config_shape.json")
            return gson.fromJson(
                fis.readBytes().toString(Charset.defaultCharset()),
                NotifyShape::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "", e)
        }
        return null
    }
}