package com.letter.notifylight.service

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.letter.notifylight.activity.NotifyLightActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "CoreService"

class CoreService : AccessibilityService() {

    override fun onInterrupt() = Unit

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d(TAG, "onAccessibilityEvent: ${event?.eventType}")
        when (event?.eventType) {
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> {
                val powerManager = ((getSystemService(Context.POWER_SERVICE)) as PowerManager?)
                if (powerManager?.isInteractive == false) {
                    GlobalScope.launch {
                        delay(5000)
                        val intent = Intent(this@CoreService, NotifyLightActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                }
            }
        }
    }

}
