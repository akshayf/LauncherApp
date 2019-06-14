package com.android.akshayfaye.launcherapp.utility

import com.android.akshayfaye.launcherapp.R

const val EXTRA_FLAG = "Shortcut flag"
const val ACTION_BROADCAST_CREATE = "android.intent.action.ACTION_POWER_CONNECTED"
const val ACTION_BROADCAST_REFRESH = "android.intent.action.ACTION_POWER_DISCONNECTED"
const val CREATE_SHORTCUTS = 1
const val REFRESH_SHORTCUTS = 2
const val INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT"
const val UNINSTALL_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT"

const val SHORTCUT_NAME_CREATE = "Create Label"
const val SHORTCUT_NAME_REFRESH = "Refresh Label"
const val DRAWABLE_CREATE = R.drawable.ic_android_blue
const val DRAWABLE_REFRESH = R.drawable.ic_android_red