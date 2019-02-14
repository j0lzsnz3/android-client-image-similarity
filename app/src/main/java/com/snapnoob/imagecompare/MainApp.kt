package com.snapnoob.imagecompare

import android.app.Application
import com.snapnoob.imagecompare.di.allModule
import org.koin.android.ext.android.startKoin

/**
 * Created by @ImamKR97
 **/
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, allModule)
    }
}