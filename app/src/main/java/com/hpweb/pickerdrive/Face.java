package com.hpweb.pickerdrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hpweb.pickerdrive.databinding.ActivityFaceBinding;
import com.model.UserModel;

import java.util.Timer;
import java.util.TimerTask;

public class Face extends AppCompatActivity {

    ActivityFaceBinding binding;
    DB db;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        if (getActionBar() != null)
            getActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_face);

        binding.version.setText("Version :" + BuildConfig.VERSION_NAME);

        db = new DB(this);
        user = db.getUser();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (user == null) {
                    startActivity(new Intent(Face.this, Login.class));
                    finish();
                } else {
//                    if (user.getEmail() == null) {
//                        startActivity(new Intent(Face.this, Login.class));
//                        finish();
//                    } else {
                        startActivity(new Intent(Face.this, DashBoard.class));
                        finish();
//                    }
                }
            }
        }, 2000);


    }
}