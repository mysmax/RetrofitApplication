package mysmax.com.retrofitapplication.webservice.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private final String baseUrl = "https://api.github.com";
    private Retrofit retrofit;
    private static final ApiClient ourInstance = new ApiClient();

    private ApiClient() {
    }
    // NO Need of this Instance
    static public ApiClient getInstance() {
        return ourInstance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null)
        {
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            retrofit = new Retrofit.Builder()
                                   .baseUrl(baseUrl)
                                   .addConverterFactory(GsonConverterFactory.create())
                                   .client(client)
                                   .build();
        }
        return retrofit;
    }

}
