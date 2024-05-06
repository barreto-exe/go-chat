package com.barreto.exe.gochat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barreto.exe.gochat.api.ApiHandler;
import com.barreto.exe.gochat.databinding.ActivityLoginBinding;
import com.barreto.exe.gochat.models.Configs;
import com.barreto.exe.gochat.models.User;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private final ApiHandler apiHandler = new ApiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = Configs.GetUserUuid(this);

        if (!userId.isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            binding.loginButton.setOnClickListener(view -> {
                createUser();
            });
        }
    }

    public void createUser(){
        String username = binding.loginUsernameText.getText().toString().trim();
        User user = new User(username);

        apiHandler.createUser(user, new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(Object data) {
                User user = (User) data;

                // Save the user uuid
                Configs.SaveUserUuid(LoginActivity.this, user.getId());

                // Go to the MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(LoginActivity.this, "Error creating user: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}