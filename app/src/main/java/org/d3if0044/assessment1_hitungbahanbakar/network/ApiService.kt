package org.d3if0044.assessment1_hitungbahanbakar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0044.assessment1_hitungbahanbakar.model.DataKonsumsi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/haririwardhana/assessment01/static-api/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL).build()

    interface ApiService {
        @GET("data.json")
        suspend fun getData(): List<DataKonsumsi>
    }
    object dataApi {
        val service: ApiService by lazy { retrofit.create(ApiService::class.java) }

            fun getDataUrl(logoId: String): String {
                return "$BASE_URL$logoId.png"
            }
        }