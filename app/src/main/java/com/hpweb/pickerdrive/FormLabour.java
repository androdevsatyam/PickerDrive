package com.hpweb.pickerdrive;

import static com.hpweb.pickerdrive.Form.Type;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hpweb.pickerdrive.databinding.ActivityFormLabourBinding;
import com.model.FormModel;

public class FormLabour extends AppCompatActivity {

    ActivityFormLabourBinding formBinding;
    String id;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_labour);

        formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_labour);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(Type);

        formBinding.city.setAdapter(Helper.getCities(FormLabour.this));
        formBinding.date.setText(Helper.getDate());

        id = Helper.getDataLocal(this, Helper.key_id);
        helper = new Helper(this);

        formBinding.submit.setOnClickListener(v -> {
            if (check()) {
                helper.showProgress("Submitting...");
                FormModel model = new FormModel(formBinding.name.getText().toString(), formBinding.date.getText().toString(), "", formBinding.mobile.getText().toString(), formBinding.address.getText().toString(), "", "", "", "", formBinding.vehicletype.getText().toString(),formBinding.labourava.getText().toString(),formBinding.labourreq.getText().toString(), formBinding.detail.getText().toString(), "", "", "", formBinding.city.getText().toString(), "", "");

                String result = Helper.submitForm(FormLabour.this, "LABOUR_Requirement", id, model);

                if (result.equalsIgnoreCase("submitted")) {
                    helper.stopProgress();
                    onBackPressed();
                } else
                    helper.stopProgress();
            }
        });
    }

    private boolean check() {
        if (formBinding.name.getText().toString().isEmpty() || formBinding.name.getText().toString().length() < 2) {
            formBinding.name.requestFocus();
            formBinding.name.setError("Name Required");
            return false;
        } else if (formBinding.mobile.getText().toString().isEmpty() || formBinding.mobile.getText().toString().length() < 9) {
            formBinding.mobile.requestFocus();
            formBinding.mobile.setError("Invalid Mobile");
            return false;
        } else
            return true;
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
            Helper.setDate(formBinding.date,d,m,year);
        };
        DatePickerDialog datePickerDialog = Helper.getDatePickerDialog(this, listener);
      /*  datePickerDialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
            Helper.setDate(formBinding.date,dayOfMonth,month,year);
        });*/
        datePickerDialog.show();
    }

}