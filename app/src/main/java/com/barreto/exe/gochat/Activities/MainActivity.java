package com.barreto.exe.gochat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barreto.exe.gochat.adapters.ChatAdapter;
import com.barreto.exe.gochat.api.ApiHandler;
import com.barreto.exe.gochat.databinding.ActivityMainBinding;
import com.barreto.exe.gochat.listeners.ChatListener;
import com.barreto.exe.gochat.models.Chat;
import com.barreto.exe.gochat.models.Configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChatListener {
    public ActivityMainBinding binding;

    private final ApiHandler apiHandler = new ApiHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createListeners();
        fetchChats();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchChats();
    }

    void createListeners()
    {
        binding.fab.setOnClickListener(view -> {
            //Go to the SelectCreationActivity
            Intent intent = new Intent(MainActivity.this, SelectCreationActivity.class);
            startActivity(intent);
        });
    }

    public void fetchChats()
    {
        String userId = Configs.GetUserUuid(this);

        apiHandler.getChats(userId, new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(Object data) {
                Chat[] chatsArray = new Chat[0];

                if(data != null) chatsArray = (Chat[]) data;

                List<Chat> chats = new ArrayList<>(Arrays.asList(chatsArray));
                ChatAdapter chatAdapter = new ChatAdapter(chats, MainActivity.this);
                binding.chatsRecyclerView.setAdapter(chatAdapter);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onChatClicked(Chat chat) {
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        intent.putExtra("chat", chat);
        startActivity(intent);
    }
}