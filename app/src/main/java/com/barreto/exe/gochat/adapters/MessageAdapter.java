package com.barreto.exe.gochat.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barreto.exe.gochat.databinding.ItemContainerReceivedMessageBinding;
import com.barreto.exe.gochat.databinding.ItemContainerSentMessageBinding;
import com.barreto.exe.gochat.models.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Message> messages;
    private final String userId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public MessageAdapter(List<Message> messages, String userId) {
        this.messages = messages;
        this.userId = userId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT)
        {
            return new SentMessageViewHolder(ItemContainerSentMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        return new ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT)
        {
            ((SentMessageViewHolder) holder).setData(messages.get(position));
        }
        else
        {
            ((ReceivedMessageViewHolder) holder).setData(messages.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getSenderId().equals(userId)){
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        ItemContainerSentMessageBinding itemBinding;
        SentMessageViewHolder(ItemContainerSentMessageBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        void setData(Message message) {
            itemBinding.messageTextView.setText(message.getContent());
            itemBinding.timeTextView.setText(message.getDateTime().toString());
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        ItemContainerReceivedMessageBinding itemBinding;
        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        void setData(Message message) {
            itemBinding.textMessage.setText(message.getContent());
            itemBinding.textCircle.setText(message.getSenderUsername().substring(0, 1));
            itemBinding.textSender.setText(message.getSenderUsername());
            itemBinding.textTime.setText(message.getDateTime().toString());
        }
    }
}
