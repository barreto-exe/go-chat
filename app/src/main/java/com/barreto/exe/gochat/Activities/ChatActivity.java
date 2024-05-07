package com.barreto.exe.gochat.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barreto.exe.gochat.api.ApiHandler;
import com.barreto.exe.gochat.databinding.ActivityChatBinding;
import com.barreto.exe.gochat.models.Chat;
import com.barreto.exe.gochat.models.Configs;
import com.barreto.exe.gochat.models.Message;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private final ApiHandler apiHandler = new ApiHandler();
    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();

        chat = (Chat) getIntent().getSerializableExtra("chat");
        if(chat != null){
            binding.titleTextView.setText(chat.getName());
            getChatMessages();
        }
    }

    void setListeners(){
        binding.backImageView.setOnClickListener(v -> finish());

        binding.infoImageView.setOnClickListener(v -> {
            //TODO: Go to the ChatInfoActivity
        });

        binding.sendLayout.setOnClickListener(v -> {
            String message = binding.messageEditText.getText().toString().trim();
            if(!message.isEmpty()){
                sendMessage(message);
            }
        });
    }

    void sendMessage(String content){
        binding.messageEditText.setText("");

        if(chat != null){
            String userId = Configs.GetUserUuid(this);
            String username = Configs.GetUsername(this);
            Date currentTime = Calendar.getInstance().getTime();

            Message message = new Message(userId, username, content, currentTime);

            apiHandler.sendMessage(chat.getId().substring(0,6), message, new ApiHandler.ApiCallback() {
                @Override
                public void onSuccess(Object data) {
                    getChatMessages();
                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(ChatActivity.this, "Error sending message", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    void getChatMessages(){
        if(chat != null){
            String chatId = chat.getId().substring(0,6);
            apiHandler.getChatMessages(chatId, new ApiHandler.ApiCallback() {
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