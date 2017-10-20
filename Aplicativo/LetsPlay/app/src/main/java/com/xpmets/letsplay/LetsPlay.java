package com.xpmets.letsplay;

import android.app.Application;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class LetsPlay extends Application {

    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
