package com.barreto.exe.gochat.activities.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.barreto.exe.gochat.R;
import com.barreto.exe.gochat.activities.MainActivity;
import com.barreto.exe.gochat.api.ApiHandler;
import com.barreto.exe.gochat.databinding.FragmentSelectCreationBinding;
import com.barreto.exe.gochat.models.Chat;
import com.barreto.exe.gochat.models.Configs;
import com.barreto.exe.gochat.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentSelectCreationBinding binding;

    private final ApiHandler apiHandler = new ApiHandler();

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentSelectCreationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ConstraintLayout layout = binding.selectLayout;
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                assert s != null;

                if (s.equals("1"))
                {
                    layout.addView(inflater.inflate(R.layout.page_create_chat, container, false));

                    TextInputEditText chatNameEditText = root.findViewById(R.id.chatNameEditText);
                    TextInputEditText chatDescriptionEditText = root.findViewById(R.id.chatDescriptionEditText);

                    root.findViewById(R.id.createChatButton).setOnClickListener(view -> {
                        createChat(getActivity(), chatNameEditText, chatDescriptionEditText);
                    });

                }
                else
                {
                    layout.addView(inflater.inflate(R.layout.page_join_chat, container, false));

                    TextInputEditText chatCodeText = root.findViewById(R.id.chatCodeText);

                    root.findViewById(R.id.joinChatButton).setOnClickListener(view -> {
                        joinChat(getActivity(), chatCodeText);
                    });
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void createChat(Activity context, TextInputEditText chatNameEditText, TextInputEditText chatDescriptionEditText){
        String creatorId = Configs.GetUserUuid(context);
        String name = chatNameEditText.getText().toString().trim();
        String description = chatDescriptionEditText.getText().toString().trim();

        Chat chat = new Chat(creatorId, name, description);

        apiHandler.createChat(chat, new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(Object data) {
                Toast.makeText(getActivity(), "Chat creado", Toast.LENGTH_LONG).show();
                context.finish();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(getActivity(), "Error creando chat. Intente de nuevo", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void joinChat(Activity context, TextInputEditText chatCodeText){
        String chatId = chatCodeText.getText().toString().trim();
        String userId = Configs.GetUserUuid(context);
        String username = Configs.GetUsername(context);

        apiHandler.joinChat(chatId, new User(userId, username), new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(Object data) {
                Toast.makeText(getActivity(), "Unido al chat", Toast.LENGTH_LONG).show();
                context.finish();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(getActivity(), "Error uniendo al chat. Intente de nuevo", Toast.LENGTH_LONG).show();
            }
        });
    }

}