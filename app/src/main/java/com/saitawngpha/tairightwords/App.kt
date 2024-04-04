package com.saitawngpha.tairightwords

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.onesignal.OneSignal
import com.saitawngpha.tairightwords.utils.Constants
import com.saitawngpha.tairightwords.utils.Constants.APPODEAL_ID
import com.saitawngpha.tairightwords.utils.Constants.APPSFLYER_ID
import com.saitawngpha.tairightwords.utils.Constants.ONE_APP
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

@HiltAndroidApp
class App: Application() {

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONE_APP)
        OneSignal.unsubscribeWhenNotificationsAreDisabled(false)
        OneSignal.promptForPushNotifications()

        //analytics = Firebase.analytics

        //appsflyer
        AppsFlyerLib.getInstance().init(APPSFLYER_ID, null, this)
        AppsFlyerLib.getInstance().start(this)
        //AppsFlyerLib.getInstance().setDebugLog(true)
    }
}