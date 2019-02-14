package com.snapnoob.imagecompare.data.network

import android.content.Context
import com.rx2androidnetworking.Rx2AndroidNetworking
import com.snapnoob.imagecompare.data.model.ResultCompare
import com.snapnoob.imagecompare.util.HttpIntercept
import io.reactivex.Single

/**
 * Created by @ImamKR97
 **/
class AppApiHelper(private val context: Context) : ApiHelper {
    override fun performCompareImage(imageEncode: String): Single<ResultCompare> =
            Rx2AndroidNetworking.post(ApiEndpoint.ENDPOINT_COMPARE_IMAGE)
                .addBodyParameter("image_encode", imageEncode)
                .setOkHttpClient(HttpIntercept.getOkHttp(context))
                .build()
                .getObjectSingle(ResultCompare::class.java)
}