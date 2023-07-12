package com.ewide.test.faris_ghilmany.core.data.source.remote.network

import com.ewide.test.faris_ghilmany.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("deals")
    suspend fun getListDeals(
        @Query("pageNumber") pageNumber : Int,
        @Query("pageSize") pageSize : Int,
        @Query("desc") desc : String,
        @Query("title") title : String? = null,
        @Query("storeId") storeId : String = "!",
    ): List<ListGameResponse>
}