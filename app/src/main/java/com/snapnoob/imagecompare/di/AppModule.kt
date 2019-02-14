package com.snapnoob.imagecompare.di

import com.docotel.libs.util.SchedulerProvider
import com.snapnoob.imagecompare.feature.adapter.ImageResultAdapter
import com.snapnoob.imagecompare.data.AppDataManager
import com.snapnoob.imagecompare.data.network.AppApiHelper
import com.snapnoob.imagecompare.feature.main.MainContract
import com.snapnoob.imagecompare.feature.main.MainPresenter
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Created by @ImamKR97
 **/

val appModule = module {
    single { CompositeDisposable() }
    single { SchedulerProvider() }
    single { AppApiHelper(androidApplication()) }
    single { AppDataManager(get()) }
}

val presenterModule = module {
    factory { MainPresenter<MainContract.View>(get(), get(), get()) }
}

val adapterModule = module {
    factory { ImageResultAdapter(ArrayList()) }
}

val allModule = listOf(appModule, presenterModule, adapterModule)