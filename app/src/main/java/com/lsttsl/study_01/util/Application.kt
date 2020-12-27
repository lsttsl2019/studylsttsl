package com.lsttsl.study_01.util

import android.app.Application
import com.appspector.sdk.AppSpector

class Application : Application() {

    init {
        instance = this
    }


    override fun onCreate() {
        super.onCreate()

        AppConfigure.initialize(this)

        AppSpector.build(this)
            .addHttpMonitor()
            .addEnvironmentMonitor()
            .addSQLMonitor()
            .addSharedPreferenceMonitor()
            .addLogMonitor()
            .run(KEY)

    }

    companion object {
        private const val KEY = "android_MTQyODBkNTYtNjllOS00NmFjLThhMmQtYTk3M2JlNzE3NWI2"

        private var instance: Application? = null


    }

}