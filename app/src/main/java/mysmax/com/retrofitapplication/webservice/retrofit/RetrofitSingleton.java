package mysmax.com.retrofitapplication.webservice.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit;
    private static String baseURL = "https://api.github.com";
    private static RetrofitSingleton instance;

    // Singleton
    private RetrofitSingleton()
    {

    }

    public synchronized static RetrofitSingleton getInstance() {
        if (instance == null)
        {
            instance = new RetrofitSingleton();
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).build();
            retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return instance;
    }

    public synchronized static Retrofit getRetrofit() {
        return retrofit;
    }
}
