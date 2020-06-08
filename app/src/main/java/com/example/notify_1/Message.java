package com.example.notify_1;

import androidx.core.app.Person;

public class Message {
    private CharSequence text;
    private long timestamp;
    private Person sender;

    public Message(CharSequence text, CharSequence sender) {
        this.text = text;
        this.sender = new Person.Builder()
                .setName(sender)
                .build();
        timestamp = System.currentTimeMillis();
    }

    public CharSequence getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Person getSender() {
        return sender;
    }
}
