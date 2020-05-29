package com.example.networkworkshopandroidclient;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

public class NetworkHandlerAsyncTask extends AsyncTask<View, String, String> {
    private TextView textView;

    @Override
    protected String doInBackground(View... views) {
        textView = (TextView) views[0];
        for (int i = 0; i < 5; i++) {
            try {
                publishProgress(String.valueOf(i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Done";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        textView.setText(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(s);
        super.onPostExecute(s);
    }
}
