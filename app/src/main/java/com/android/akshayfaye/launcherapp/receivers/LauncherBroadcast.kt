package com.android.akshayfaye.launcherapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.android.akshayfaye.launcherapp.services.LauncherService
import com.android.akshayfaye.launcherapp.utility.*

class LauncherBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        var extraFlag : Int = CREATE_SHORTCUTS

        when(intent?.action){

            ACTION_AIRPLANE_MODE -> extraFlag = CREATE_SHORTCUTS

            ACTION_WIFI_STATE_CHANGED -> extraFlag = REFRESH_SHORTCUTS

        }

        val intent = Intent(context, LauncherService::class.java)
        intent.putExtra(EXTRA_FLAG, extraFlag)
        context?.startService(intent)
    }
}