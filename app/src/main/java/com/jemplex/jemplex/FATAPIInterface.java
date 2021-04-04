package com.jemplex.jemplex;
import com.jemplex.jemplex.response.AppMenu;
import com.jemplex.jemplex.response.Authenticate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface FATAPIInterface {
    @POST("fat/User/authenticate")
    Call<Authenticate> authUser(@Body Authenticate user);

    @GET("fat/AppMenu")
    Call <List<AppMenu>> GetMenu(@Header("Authorization") String Token);
}
