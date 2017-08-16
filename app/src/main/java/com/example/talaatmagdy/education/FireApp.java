package com.example.talaatmagdy.education;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by talaatmagdy on 5/22/17.
 */

public class FireApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
