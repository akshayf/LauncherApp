package com.android.akshayfaye.launcherapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.android.akshayfaye.launcherapp.services.LauncherService
import com.android.akshayfaye.launcherapp.utility.*

class LauncherBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        var extraFlag : Int = CREATE_SHORTCUTS
        val TAG = "LauncherBroadcast"

        when(intent?.action) {

            ACTION_BROADCAST_CREATE -> {
                extraFlag = CREATE_SHORTCUTS
                Log.e(TAG, "ACTION_AIRPLANE_MODE")
            }

            ACTION_BROADCAST_REFRESH -> {
                extraFlag = REFRESH_SHORTCUTS
                Log.e(TAG, "ACTION_WIFI_STATE_CHANGED")
            }

            else -> CREATE_SHORTCUTS
        }

        val serviceIntent = Intent(context, LauncherService::class.java)
        serviceIntent.putExtra(EXTRA_FLAG, extraFlag)
        context?.startService(serviceIntent)
    }
}