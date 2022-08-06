package com.hpweb.pickerdrive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class About extends Fragment {

    FloatingActionButton mail, call,location;
    String msg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mail = view.findViewById(R.id.support_email);
        call = view.findViewById(R.id.support_call);
        location = view.findViewById(R.id.support_address);

        msg = "Hello I'm using " + getResources().getString(R.string.app_name);


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.supportemail)});
                i.putExtra(Intent.EXTRA_SUBJECT, "Logistic Support");
                i.putExtra(Intent.EXTRA_TEXT, msg);
                i.setType("plain/text");
                Helper.makeToast(getContext(), "Select Mail..", Toast.LENGTH_SHORT);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (Exception ex) {
                    Helper.makeToast(getContext(), "There are no email clients installed..", Toast.LENGTH_SHORT);
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.supportcall)));
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?&daddr=2241/12 Marriwala Town Manimajra Chandigarh 160101"));
                startActivity(intent);
            }
        });

    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        mail = findViewById(R.id.support_email);
        call = findViewById(R.id.support_call);
        location = findViewById(R.id.support_address);

        msg = "Hello I'm using " + getResources().getString(R.string.app_name);

      *//*  if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("About Us");*//*


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.supportemail)});
                i.putExtra(Intent.EXTRA_SUBJECT, "Logistic Support");
                i.putExtra(Intent.EXTRA_TEXT, msg);
                i.setType("plain/text");
                Helper.makeToast(getContext(), "Select Mail..", Toast.LENGTH_SHORT);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (Exception ex) {
                    Helper.makeToast(getContext(), "There are no email clients installed..", Toast.LENGTH_SHORT);
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.supportcall)));
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?&daddr=2241/12 Marriwala Town Manimajra Chandigarh 160101"));
                startActivity(intent);
            }
        });

    }*/
}