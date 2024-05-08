package com.barreto.exe.gochat.api;

import com.barreto.exe.gochat.models.Chat;
import com.barreto.exe.gochat.models.Message;
import com.barreto.exe.gochat.models.User;

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

    public static final String HTTP = "http://";
    public static final String BASE_URL = "192.168.1.103:8080";

    // Retrofit setup
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HTTP + BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ApiService apiService = retrofit.create(ApiService.class);

    // Define your API interface
    interface ApiService {
        @POST("/users")
        Call<User> createUser(@Body User user);

        @GET("/{id-user}/chats")
        Call<Chat[]> getChats(@Path("id-user") String userId);

        @POST("/chats")
        Call<Chat> createChat(@Body Chat chat);

        @GET("/chats/{id-chat}/messages")
        Call<Message[]> getChatMessages(@Path("id-chat") String chatId);

        @POST("/chats/{id-chat}/messages")
        Call<Void> sendMessage(@Path("id-chat") String chatId, @Body Message message);

        @POST("/chats/{id-chat}/users")
        Call<Void> joinChat(@Path("id-chat") String chatId, @Body User user);

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

    public void getChats(final String userId, final ApiCallback callback) {
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

    public void createChat(final Chat chat, final ApiCallback callback) {
        Call<Chat> call = apiService.createChat(chat);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void getChatMessages(final String chatId, final ApiCallback callback) {
        Call<Message[]> call = apiService.getChatMessages(chatId);
        call.enqueue(new Callback<Message[]>() {
            @Override
            public void onResponse(Call<Message[]> call, Response<Message[]> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Message[]> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void sendMessage(final String chatId, final Message message, final ApiCallback callback) {
        Call<Void> call = apiService.sendMessage(chatId, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    public void joinChat(final String chatId, final User user, final ApiCallback callback) {
        Call<Void> call = apiService.joinChat(chatId, user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
