package com.barreto.exe.gochat.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barreto.exe.gochat.api.ApiHandler;
import com.barreto.exe.gochat.databinding.ActivityChatBinding;
import com.barreto.exe.gochat.models.Chat;
import com.barreto.exe.gochat.models.Message;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private final ApiHandler apiHandler = new ApiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();

        Chat chat = (Chat) getIntent().getSerializableExtra("chat");
        if(chat != null){
            binding.titleTextView.setText(chat.getName());
            getChatMessages(chat);
        }
    }

    void setListeners(){
        binding.backImageView.setOnClickListener(v -> finish());

        binding.infoImageView.setOnClickListener(v -> {
            //TODO: Go to the ChatInfoActivity
        });
    }

    void getChatMessages(Chat chat){
        if(chat != null){
            apiHandler.getChatMessages(chat.getId().substring(0,6), new ApiHandler.ApiCallback() {
                @Override
                public void onSuccess(Object data) {
                    Message[] messages = (Message[]) data;

                    binding.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(ChatActivity.this, "Error getting chat messages", Toast.LENGTH_LONG).show();
                    binding.progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}