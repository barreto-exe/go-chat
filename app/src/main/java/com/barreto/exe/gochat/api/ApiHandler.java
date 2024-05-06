package com.barreto.exe.gochat.api;

import com.barreto.exe.gochat.models.Chat;
import com.barreto.exe.gochat.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiHandler {

    private static final String BASE_URL = "http://192.168.1.100:8080";

    // Retrofit setup
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ApiService apiService = retrofit.create(ApiService.class);

    // Define your API interface
    interface ApiService {
        @POST("/users")
        Call<User> createUser(@Body User user);

        @GET("/{id-user}/chats")
        Call<Chat[]> getChats(@Path("id-user") String userId);
    }

    public void createUser(final User user, final ApiCallback callback) {
        Call<User> call = apiService.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void fetchChats(final String userId, final ApiCallback callback) {
        Call<Chat[]> call = apiService.getChats(userId);
        call.enqueue(new Callback<Chat[]>() {
            @Override
            public void onResponse(Call<Chat[]> call, Response<Chat[]> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Chat[]> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    // Generic Callback interface for handling results
    public interface ApiCallback {
        void onSuccess(Object data);
        void onFailure(String error);
    }
}
