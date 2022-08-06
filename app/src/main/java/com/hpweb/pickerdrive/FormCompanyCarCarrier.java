package com.hpweb.pickerdrive;

import static com.hpweb.pickerdrive.Form.Type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import com.hpweb.pickerdrive.databinding.ActivityFormCompanyCarCarrierBinding;

public class FormCompanyCarCarrier extends AppCompatActivity {

    ActivityFormCompanyCarCarrierBinding bindin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_company_car_carrier);

        bindin = DataBindingUtil.setContentView(this, R.layout.activity_form);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(Type);
//        bindin.formType.setText(Type);

    }

    public void showDate(View bindview) {
        DatePickerDialog.OnDateSetListener listener= (datePicker, year, month, dayOfMonth) -> {
            month=month+1;
            String m= String.valueOf(month),d= String.valueOf(dayOfMonth);
            if(month < 10){
                m = "0" + month;
            }
            if(dayOfMonth < 10){
                d  = "0" + dayOfMonth;
            }
            bindin.date.setText(year + "-" + m + "-" + d);
        };

        DatePickerDialog datePickerDialog = Helper.getDatePickerDialog(this, listener);
       /* datePickerDialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
            bindin.date.setText(dayOfMonth + "/" + month + "/" + year);
        });*/
        datePickerDialog.show();
    }

}