package space.reul.cleanarchitectureexample.data.webapi

import android.util.Log
import com.google.gson.Gson
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import space.reul.cleanarchitectureexample.domain.model.Cats

private const val API_URL: String = "https://cataas.com/api/"

private val curlInterceptor =
    CurlInterceptor(
        object : Logger {
            override fun log(message: String) {
                Log.d("CURL", message)
            }
        },
    )

class CatService {
    private val okHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(curlInterceptor)
// add if needed
//      .addInterceptor(AuthorizationInterceptor)
            .build()

    private val retrofit: Retrofit =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    private val webApi = retrofit.create(WebApi::class.java)

    suspend fun listUrls(): Result<Cats> {
        val response = webApi.listUrls()
        return kotlin.runCatching {
            response.body() ?: arrayListOf()
        }
    }

    private interface WebApi {
        @GET("cats")
        suspend fun listUrls(
            @Query("limit") count: Int = 18,
            @Query("skip") skip: Int = 0
        ): Response<Cats>
    }
}
