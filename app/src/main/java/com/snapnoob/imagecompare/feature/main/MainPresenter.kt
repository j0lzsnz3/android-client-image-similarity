package com.snapnoob.imagecompare.feature.main

import com.docotel.libs.base.presenter.BasePresenter
import com.docotel.libs.util.SchedulerProvider
import com.snapnoob.imagecompare.data.AppDataManager
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by @ImamKR97
 **/
class MainPresenter<V: MainContract.View>(private val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(schedulerProvider, compositeDisposable), MainContract.Presenter<V> {

    override fun doCompareImages(imageEncode: String) {
        getView()?.showProgressBar()
        compositeDisposable.add(appDataManager.performCompareImage(imageEncode)
            .compose(schedulerProvider.ioToMainSingleScheduler())
            .subscribe({ resultCompare ->
                getView()?.showResultCompare(resultCompare.response)
                getView()?.hideProgressBar()
            }, {
                getView()?.showError(it.message)
                getView()?.hideProgressBar()
            }))
    }
}