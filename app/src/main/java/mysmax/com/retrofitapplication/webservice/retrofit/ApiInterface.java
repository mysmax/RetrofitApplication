package mysmax.com.retrofitapplication.webservice.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mysmax.com.retrofitapplication.model.ClassName;
import mysmax.com.retrofitapplication.webservice.retrofit.EventProperties.Event;
import mysmax.com.retrofitapplication.webservice.retrofit.feedProperties.Feeds;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/feeds")
    Call<FeedProperties> getApiJson();

    @GET("/feeds")
    Call<Feeds> getFeeds();

    @GET("/events")
    Call<List<Event>> getEvents();

    // Path, Body, Field, Query, QueryMap, Part

    // Field Params
    @FormUrlEncoded
    @POST("/api/userlogin")
    Call<ResponseBody>  getUserLogin(
            @Field("client_id") String id,
            @Field("client_secret") String secret,
            @Field("username") String uname,
            @Field("password") String password
    );

    // Field Map
    @FormUrlEncoded
    @POST("/oauth/access_token")
    void getToken(
            @FieldMap Map<String, String> params,
            Callback<ResponseBody> callback
    );

    // Body with Class object with get and set etc.
    @POST("/jayson")
    ClassName postJson(@Body ClassName body);

    @POST("/jayson")
    ClassName postJson(@Body HashMap<String, Object> body);

    // Path
    @POST("prefix/user/{login}")
    Call<ResponseBody> login(@Path("login") String postfix, @Body RequestBody params);

    // Query
    //-> https://api.test.com/Search?one=Whatever&two=here&key=SFSDF24242353434
    //@GET("Search") //i.e https://api.test.com/Search?
    //Call<Products> getProducts(@Query("one") String one, @Query("two") String two, @Query("key") String key)

    // QueryMap
    // http://your.api.url/news?page=2&author=Marcus
    // Map<String, String> data = new HashMap<>();
    //    data.put("author", "Marcus");
    //    data.put("page", String.valueOf(2));
    //@GET("/news")
    //Call<List<News>> getNews(@QueryMap Map<String, String> options);

    // Part
    // check https://futurestud.io/tutorials/retrofit-2-passing-multiple-parts-along-a-file-with-partmap

}
