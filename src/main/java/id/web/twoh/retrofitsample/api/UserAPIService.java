package id.web.twoh.retrofitsample.api;

import id.web.twoh.retrofitsample.model.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hafizh Herdi on 5/15/2016.
 */
public interface UserAPIService {

    @GET("api")
    Call<Result> getResultInfo();

    @GET("api")
    Call<ResponseBody> getResultAsJSON();
}
