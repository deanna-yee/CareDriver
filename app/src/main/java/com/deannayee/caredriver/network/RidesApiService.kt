package com.deannayee.caredriver.network

import com.deannayee.caredriver.network.models.RideData
import com.deannayee.caredriver.network.models.Trip
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://storage.googleapis.com/hsd-interview-resources/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
                        .baseUrl(BASE_URL).build()
interface RidesApiService {
    @GET("simplified_my_rides_response.json")
    suspend fun getRides(): RideData
}
object RidesApi{
    val retrofitService : RidesApiService by lazy {
        retrofit.create(RidesApiService::class.java)
    }
}