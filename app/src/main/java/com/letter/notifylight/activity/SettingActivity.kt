package com.letter.notifylight.activity

import android.content.isDarkTheme
import android.content.startActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.letter.notifylight.LetterApplication
import com.letter.notifylight.R
import com.letter.notifylight.databinding.ActivitySettingBinding
import com.letter.notifylight.viewmodel.SettingViewModel
import com.letter.presenter.ViewPresenter

class SettingActivity : AppCompatActivity(), ViewPresenter {

    private lateinit var binding: ActivitySettingBinding
    private val model by lazy {
        ViewModelProvider
            .AndroidViewModelFactory(LetterApplication.instance())
            .create(SettingViewModel::class.java)
    }

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

        initBinding()
        initModel()
    }

    private fun initBinding() {
        binding.let {
            it.presenter = this@SettingActivity
        }
    }

    private fun initModel() {
        model.apply {
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.preview_button -> startActivity(NotifyLightActivity::class.java)
        }
    }
}