package com.android.akshayfaye.launcherapp.services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class LauncherService : Service() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}