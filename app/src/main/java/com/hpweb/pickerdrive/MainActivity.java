package com.hpweb.pickerdrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hpweb.pickerdrive.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DB db;

    String[] title = new String[]{
            "History",
            "PACKERS VEHICLE Requirement",
            "PACKERS CAR CARRIER Requirement",
            "COMPANY VEHICLE Requirement",
            "TRANSPORTERS VEHICLE Requirement",
            "TRANSPORTERS LOAD Requirement",
            "DRIVER LOAD Requirement",
            "CAR CARRIER COMPANY LOAD Requirement",
            "CUSTOMER VEHICLE Requirement",
            "STORAGE Requirement",
            "LABOUR Requirement",
    };

    int[] icon = new int[]{
            R.drawable.men_history,
            R.drawable.ic_packer,
            R.drawable.ic_packer,
            R.drawable.ic_driver_company,
            R.drawable.ic_transporter,
            R.drawable.ic_transporter,
            R.drawable.ic_driving,
            R.drawable.ic_driver_company,
            R.drawable.car,
            R.drawable.ic_boxes,
            R.drawable.ic_workers
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getActionBar()!=null)
            getActionBar().setTitle("Requirement Form");

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("Requirement Form");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        db=new DB(MainActivity.this);

        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
//        binding.list.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        MainAdapter adapter = new MainAdapter(this, title, icon);
        binding.list.setAdapter(adapter);

        if(db.getUser()!=null)
            Helper.setDataLocal(this,db.getUser());

    }

    public void showHistory(View view) {
        Intent intent=new Intent(MainActivity.this,Transaction.class);
        startActivity(intent);
    }

    public void showAbout(View view) {
        Intent intent=new Intent(MainActivity.this,About.class);
        startActivity(intent);
    }
}