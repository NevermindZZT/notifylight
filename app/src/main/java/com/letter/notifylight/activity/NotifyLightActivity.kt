package com.letter.notifylight.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.lifecycle.*
import com.letter.notifylight.LetterApplication
import com.letter.notifylight.R
import com.letter.notifylight.databinding.ActivityNotifyLightBinding
import com.letter.notifylight.viewmodel.NotifyLightViewModel
import com.letter.presenter.ViewPresenter

class NotifyLightActivity : AppCompatActivity(), ViewPresenter {

    private lateinit var binding: ActivityNotifyLightBinding
    private val model by lazy {
        ViewModelProvider
            .AndroidViewModelFactory(LetterApplication.instance())
            .create(NotifyLightViewModel::class.java)
    }
    private var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifyLightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        setShowWhenLocked(true)
        setTurnScreenOn(true)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        val powerManager = (getSystemService(POWER_SERVICE) as PowerManager?)
        val wakeLock = powerManager?.newWakeLock(
            PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_DIM_WAKE_LOCK,
            "TAG:")
        wakeLock?.acquire()

        initBinding()
        initModel()
    }

    private fun initBinding() {
        binding.let {
            it.presenter = this
        }
        binding.notifyView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_flash))
    }

    private fun initModel() {
        model.apply {
            notifyShape.observe(this@NotifyLightActivity) {
                binding.notifyView.notifyShape = it
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.exit_button -> {
                if (System.currentTimeMillis() - time < DOUBLE_CLICK_TIME) {
                    finish()
                }
                time = System.currentTimeMillis()
            }
        }
    }

    companion object {
        private const val DOUBLE_CLICK_TIME = 500
    }
}