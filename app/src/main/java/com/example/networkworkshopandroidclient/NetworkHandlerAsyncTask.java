package com.example.networkworkshopandroidclient;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkHandlerAsyncTask extends AsyncTask<View, String, String> {
    private TextView textView;
    public TextView serverMessageTextView;
    private DataOutputStream dos;
    @Override
    protected String doInBackground(View... views) {
        textView = (TextView) views[0];
        serverMessageTextView = (TextView) views[0];
//        for (int i = 0; i < 5; i++) {
//            try {
//                publishProgress(String.valueOf(i));
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            Socket socket = new Socket("192.168.1.56", 3000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String message = dis.readUTF();
                publishProgress(message);
            }
//                    Log.i("SOCKET", dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Done";
    }
    public void sendMessage(String message){
        final String finalMessage = message;

        Thread senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF(finalMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        senderThread.start();

    }

    @Override
    protected void onProgressUpdate(String... values) {
//        textView.setText(values[0]);
        serverMessageTextView.setText(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(s);
        super.onPostExecute(s);
    }
}
