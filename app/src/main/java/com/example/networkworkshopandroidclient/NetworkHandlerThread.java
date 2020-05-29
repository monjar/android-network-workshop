package com.example.networkworkshopandroidclient;

import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkHandlerThread extends Thread {
    public TextView serverMessageTextView;
    private DataOutputStream dos;
    NetworkHandlerThread(TextView serverMessageTextView){
        super();
        this.serverMessageTextView = serverMessageTextView;
    }

    @Override
    public void run() {
        super.run();
        try {
            Socket socket = new Socket("192.168.1.56", 3000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String message = dis.readUTF();
                serverMessageTextView.setText(message);
            }
//                    Log.i("SOCKET", dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
