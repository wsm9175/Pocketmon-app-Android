package com.wsm9175.android.pocketmon.network.model

data class Response(
    val count : Int,
    val previous: String?,
    val next: String?,
    val results: List<Result>
){
    data class Result(
        val url: String,
        val name: String
    )
}
