package com.android.akshayfaye.launcherapp.ui

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.akshayfaye.launcherapp.R
import com.android.akshayfaye.launcherapp.receivers.LauncherBroadcast
import com.android.akshayfaye.launcherapp.utility.ACTION_BROADCAST_CREATE
import com.android.akshayfaye.launcherapp.utility.ACTION_BROADCAST_REFRESH

class MainActivity : AppCompatActivity() {

    private val launcherBroadcast : LauncherBroadcast = LauncherBroadcast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_BROADCAST_CREATE)
        intentFilter.addAction(ACTION_BROADCAST_REFRESH)

        registerReceiver(launcherBroadcast, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(launcherBroadcast)
    }
}
