package com.snapnoob.imagecompare.data.network

import com.snapnoob.imagecompare.data.model.ResultCompare
import io.reactivex.Single

/**
 * Created by @ImamKR97
 **/
interface ApiHelper {
    fun performCompareImage(imageEncode: String) : Single<ResultCompare>
}