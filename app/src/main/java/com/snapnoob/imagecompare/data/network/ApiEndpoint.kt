package com.snapnoob.imagecompare.data.network

import com.snapnoob.imagecompare.BuildConfig
import com.snapnoob.imagecompare.data.model.ResultCompare
import io.reactivex.Single

/**
 * Created by @ImamKR97
 **/
object ApiEndpoint {
    const val ENDPOINT_COMPARE_IMAGE = BuildConfig.BASE_URL + "api/compare/image"
}