package com.android.akshayfaye.launcherapp.ui

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.akshayfaye.launcherapp.R
import com.android.akshayfaye.launcherapp.receivers.LauncherBroadcast
import com.android.akshayfaye.launcherapp.utility.ACTION_AIRPLANE_MODE
import com.android.akshayfaye.launcherapp.utility.ACTION_WIFI_STATE_CHANGED

class MainActivity : AppCompatActivity() {

    private val launcherBroadcast : LauncherBroadcast = LauncherBroadcast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_AIRPLANE_MODE)
        intentFilter.addAction(ACTION_WIFI_STATE_CHANGED)

        registerReceiver(launcherBroadcast, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(launcherBroadcast)
    }
}
