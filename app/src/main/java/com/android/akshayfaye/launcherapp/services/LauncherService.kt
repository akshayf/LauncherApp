package com.android.akshayfaye.launcherapp.services

import android.annotation.TargetApi
import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.android.akshayfaye.launcherapp.R
import com.android.akshayfaye.launcherapp.utility.*
import java.util.ArrayList

class LauncherService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.getIntExtra(EXTRA_FLAG, -1) != -1) {
            initShortcut(intent?.getIntExtra(EXTRA_FLAG, CREATE_SHORTCUTS))
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initShortcut(extraFlag : Int?){

        if (Build.VERSION.SDK_INT < 26) {
            shortcutsForOldVersions(extraFlag)
        } else {
            createOrRefreshShortcutsForLatestVersions(extraFlag)
        }

        stopSelf()
    }

    private fun shortcutsForOldVersions(extraFlag : Int?){

        if(extraFlag == CREATE_SHORTCUTS){
            createOrRefreshShortcutsForOldVersions(INSTALL_SHORTCUT, SHORTCUT_NAME_CREATE, DRAWABLE_CREATE)
        }else if(extraFlag == REFRESH_SHORTCUTS){
            createOrRefreshShortcutsForOldVersions(UNINSTALL_SHORTCUT, "", 0)
            createOrRefreshShortcutsForOldVersions(INSTALL_SHORTCUT, SHORTCUT_NAME_REFRESH, DRAWABLE_REFRESH)
        }
    }

    private fun createOrRefreshShortcutsForOldVersions(action : String, shortcutName : String, iconId : Int) {

        //val shortcutIntent = Intent(applicationContext, TestActivity::class.java)
        // shortcutIntent.action = Intent.ACTION_MAIN

        val addIntent = Intent()
        /*addIntent
            .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)*/

        if(action == INSTALL_SHORTCUT){
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName)
            addIntent.putExtra(
                Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(
                    applicationContext,
                    iconId
                )
            )
        }

        //addIntent.putExtra("duplicate", false)
        addIntent.action = action
        applicationContext.sendBroadcast(addIntent)
    }


    @TargetApi(26)
    private fun createOrRefreshShortcutsForLatestVersions(extraFlag : Int?){

        val shortcutManager = getSystemService(ShortcutManager::class.java)

        if(extraFlag == CREATE_SHORTCUTS){

            //val intent = Intent(applicationContext, TestActivity::class.java)
            //intent.action = Intent.ACTION_MAIN
            val shortcut = ShortcutInfo.Builder(this, SHORTCUT_NAME_CREATE)
                //.setIntent(intent)
                .setIcon(
                    Icon.createWithResource(this, DRAWABLE_CREATE)
                )
                .setShortLabel(this.getString(R.string.app_name))
                .build()
            shortcutManager!!.requestPinShortcut(shortcut, null)

        }else if(extraFlag == REFRESH_SHORTCUTS){

            val shortcuts = shortcutManager!!.pinnedShortcuts

            val updateList = ArrayList<ShortcutInfo>()

            if (shortcuts.size > 0) {
                val shortcutInfo = shortcuts[0]
                val refresh = ShortcutInfo.Builder(
                    this, shortcutInfo.id
                )
                refresh.setIcon(Icon.createWithResource(this, DRAWABLE_REFRESH))
                refresh.setShortLabel(SHORTCUT_NAME_REFRESH)
                updateList.add(refresh.build())
                shortcutManager.updateShortcuts(updateList)
            }
        }
    }
}