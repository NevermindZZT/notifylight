package com.letter.notifylight.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.letter.notifylight.R
import com.letter.notifylight.databinding.ActivityNotifyLightBinding
import com.letter.presenter.ViewPresenter

class NotifyLightActivity : AppCompatActivity(), ViewPresenter {

    private lateinit var binding: ActivityNotifyLightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifyLightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setShowWhenLocked(true)
        setTurnScreenOn(true)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val powerManager = (getSystemService(POWER_SERVICE) as PowerManager?)
        val wakeLock = powerManager?.newWakeLock(
            PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_DIM_WAKE_LOCK,
            "TAG:")
        wakeLock?.acquire(10000)

        initBinding()
    }

    private fun initBinding() {
        binding.let {
            it.presenter = this
        }
        binding.notifyView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_flash))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.exit_button -> finish()
        }
    }
}