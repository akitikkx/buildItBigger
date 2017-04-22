package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import za.co.ahmed.builditbigger.backend.myApi.MyApi;
import za.co.ahmedtikiwa.jokedisplaylib.JokeDisplayActivity;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();
    private static final String LOCAL_ROOT_URL = "http://10.0.2.2:8080/_ah/api";
    private MyApi myApiService = null;
    private Activity activity;
    private ProgressDialog dialog;


    public EndpointsAsyncTask(Activity activity) {
        this.activity = activity;
        this.dialog  = new ProgressDialog(this.activity);
        this.dialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        if (this.dialog != null) {
            this.dialog.setMessage(activity.getString(R.string.joke_loading_progress_message));
            if (!this.dialog.isShowing()) {
                this.dialog.show();
            }
        }
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setApplicationName(activity.getString(R.string.app_name))
                    .setRootUrl(LOCAL_ROOT_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

        Intent displayJoke = new Intent(activity, JokeDisplayActivity.class);
        displayJoke.putExtra(JokeDisplayActivity.JOKE_EXTRA, result);
        activity.startActivity(displayJoke);
    }
}
