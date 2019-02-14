package com.snapnoob.imagecompare.feature.main

import com.docotel.libs.base.presenter.BasePresenterContract
import com.docotel.libs.base.view.BaseViewContract
import com.snapnoob.imagecompare.data.model.ResultResponse

/**
 * Created by @ImamKR97
 **/
interface MainContract {
    interface View: BaseViewContract {
        fun showResultCompare(resultData: ResultResponse)
    }

    interface Presenter<V: View>: BasePresenterContract<V> {
        fun doCompareImages(imageEncode: String)
    }
}