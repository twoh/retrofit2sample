package id.web.twoh.retrofitsample.api;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Hafizh Herdi on 10/15/2016.
 */

public interface TWOHAPIService {

    @GET("storyofme")
    Call<ResponseBody> getStoryOfMe(@QueryMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("post_message")
    Call<ResponseBody> postMessage(@FieldMap HashMap<String, String> params);

}
