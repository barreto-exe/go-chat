package com.barreto.exe.gochat.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.barreto.exe.gochat.adapters.ChatAdapter;
import com.barreto.exe.gochat.databinding.ActivityMainBinding;
import com.barreto.exe.gochat.models.Chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    //array of chats
    private List<Chat> chats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fillChats();
    }

    public void fillChats()
    {
        chats.add(new Chat("1", "Los pavos", "John Doe", "Hey there! everyone", new Date(2024,1,1)));
        chats.add(new Chat("2", "Los pavos", "John Doe", "Hey there! everyone", new Date(2024,1,1)));
        chats.add(new Chat("3", "Los pavos", "John Doe", "Hey there! everyone", new Date(2024,1,1)));

        ChatAdapter chatAdapter = new ChatAdapter(chats);
        binding.chatsRecyclerView.setAdapter(chatAdapter);
    }
}