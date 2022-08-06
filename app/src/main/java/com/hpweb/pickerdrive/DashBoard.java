package com.hpweb.pickerdrive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.hpweb.pickerdrive.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {

    ActivityDashBoardBinding dashBoardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        dashBoardBinding= DataBindingUtil.setContentView(this,R.layout.activity_dash_board);

        changeTitle("Home");

        changeScreen(new Home());

        dashBoardBinding.btmNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        changeTitle("Home");
                        changeScreen(new Home());
                        break;
                    case R.id.service:
                        changeTitle("Services");
                        changeScreen(new Packer());
                        break;
                    case R.id.about:
                        changeTitle("About Us");
                        changeScreen(new About());
                }
                return true;
            }
        });
    }

    private void changeTitle(String title) {
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(title);
    }


    void changeScreen(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame,fragment).commit();
    }
}