package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChatActivity extends AppCompatActivity {

    String chatUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        chatUser = intent.getStringExtra("username");

        setTitle("Wattsapp - Chat with " + chatUser);
    }
}
