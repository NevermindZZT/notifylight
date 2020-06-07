package com.letter.notifylight.activity

import android.content.isDarkTheme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.letter.notifylight.R
import com.letter.notifylight.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isDarkTheme()) {
            /* Android O以上支持，设置浅色状态栏 */
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        /* 设置Action Bar并使能home按钮 */
        setSupportActionBar(binding.toolbar)
    }
}