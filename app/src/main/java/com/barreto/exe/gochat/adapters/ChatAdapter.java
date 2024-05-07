package com.barreto.exe.gochat.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barreto.exe.gochat.databinding.ItemContainerUserBinding;
import com.barreto.exe.gochat.models.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final List<Chat> chats;
    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemBinding = ItemContainerUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChatViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.setData(chats.get(position));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        ItemContainerUserBinding itemBinding;
        ChatViewHolder(ItemContainerUserBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        void setData(Chat chat) {
            // If the chat has no messages, show the last message as "No messages"
            String message;
            if(chat.getLastMessage().isEmpty()){
                message = "No messages";
            } else {
                message = chat.getLastMessageUsername() + ": " + chat.getLastMessage();
            }

            itemBinding.textCircle.setText(chat.getName().substring(0, 1));
            itemBinding.textName.setText(chat.getName());
            itemBinding.textMessage.setText(message);
        }

    }
}
