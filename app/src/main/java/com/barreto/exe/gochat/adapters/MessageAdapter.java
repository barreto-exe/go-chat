package com.barreto.exe.gochat.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.barreto.exe.gochat.databinding.ItemContainerReceivedMessageBinding;
import com.barreto.exe.gochat.databinding.ItemContainerSentMessageBinding;
import com.barreto.exe.gochat.models.Message;

public class MessageAdapter {
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
            itemBinding.textCircle.setText(message.getSenderId().substring(0, 1));
            itemBinding.textSender.setText(message.getSenderUsername());
            itemBinding.textTime.setText(message.getDateTime().toString());
        }
    }

}
