package com.ewide.test.faris_ghilmany.core.data.source.remote.network

import com.ewide.test.faris_ghilmany.core.data.source.remote.response.DetailGameResponse
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

interface GameApiService {
    @GET("deals")
    suspend fun getListDeals(
        @Query("pageNumber") pageNumber : Int,
        @Query("pageSize") pageSize : Int,
        @Query("desc") desc : String? = "0",
        @Query("title") title : String? = null,
        @Query("storeId") storeId : String = "1",
    ): List<ListGameResponse>

    @GET("deals")
    suspend fun getDetailDeals(
        @Query("id", encoded = true) id: String
    ): DetailGameResponse{
        return DetailGameResponse()
    }

}