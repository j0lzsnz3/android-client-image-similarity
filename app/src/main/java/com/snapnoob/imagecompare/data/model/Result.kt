package com.snapnoob.imagecompare.data.model

data class ResultCompare(
    val response: ResultResponse
)

data class ResultResponse(
    val image_to_compare: String,
    val result: List<ResultData>
)

data class ResultData(
    val compared_with: String,
    val img_url: String,
    val similarity: Double
)