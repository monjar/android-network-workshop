package com.example.networkworkshopandroidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private NetworkHandlerThread networkHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        final Button button = findViewById(R.id.submit);
        NetworkHandlerAsyncTask at = new NetworkHandlerAsyncTask();
        at.execute(textView);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                submitText();
//            }
//        });
//        networkHandlerThread = new NetworkHandlerThread(textView);
//        networkHandlerThread.start();

    }

    public void submitText() {
        final EditText editText = findViewById(R.id.editText);
        networkHandlerThread.sendMessage(editText.getText().toString());
    }
}
