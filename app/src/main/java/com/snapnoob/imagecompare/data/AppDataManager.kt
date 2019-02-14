package com.snapnoob.imagecompare.data

import com.snapnoob.imagecompare.data.model.ResultCompare
import com.snapnoob.imagecompare.data.network.AppApiHelper
import io.reactivex.Single

/**
 * Created by @ImamKR97
 **/
class AppDataManager(private val appApiHelper: AppApiHelper) : DataManager {
    override fun performCompareImage(imageEncode: String): Single<ResultCompare> =
            appApiHelper.performCompareImage(imageEncode)
}