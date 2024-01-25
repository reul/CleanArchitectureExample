package space.reul.cleanarchitectureexample.data.webapi

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private typealias Urls = ArrayList<String>
private const val API_URL: String = "https://shibe.online/api/"

class ShibaService {
    private val okHttpClient = OkHttpClient()
        .newBuilder()
//        .addInterceptor(RequestLoggingInterceptor())
// add if needed
//      .addInterceptor(AuthorizationInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    private val webApi = retrofit.create(WebApi::class.java)

    suspend fun listUrls(): ArrayList<String> {
        val response = webApi.listUrls()
        return response.body() ?: arrayListOf()
    }

    private interface WebApi {
        @GET("shibes?urls=true&httpsUrls=true")
        suspend fun listUrls(@Query("count") count: Int = 12): Response<Urls>
    }
}
