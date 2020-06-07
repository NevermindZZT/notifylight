package com.letter.notifylight.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.letter.notifylight.R
import com.letter.notifylight.databinding.ActivityNotifyLightBinding
import com.letter.presenter.ViewPresenter

private const val TAG = "NotifyLightActivity"

class NotifyLightActivity : AppCompatActivity(), ViewPresenter {

    private lateinit var binding: ActivityNotifyLightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifyLightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setShowWhenLocked(true)
        setTurnScreenOn(true)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                or WindowManager.LayoutParams.FLAG_FULLSCREEN)

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
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.exit_button -> finish()
        }
    }
}