package com.hpweb.pickerdrive;

import static com.hpweb.pickerdrive.Form.Type;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hpweb.pickerdrive.databinding.ActivityFormDriverLoadBinding;
import com.model.FormModel;

public class FormDriverLoad extends AppCompatActivity {

    ActivityFormDriverLoadBinding formBinding;
    String id,requirement;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_driver_load);

        formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_driver_load);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(Type);

        formBinding.from.setAdapter(Helper.getCities(FormDriverLoad.this));
        formBinding.to.setAdapter(Helper.getCities(FormDriverLoad.this));
        formBinding.date.setText(Helper.getDate());

        id = Helper.getDataLocal(this, Helper.key_id);
        helper = new Helper(this);

        formBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    if(formBinding.requirement.getCheckedRadioButtonId()==R.id.fullload)
                        requirement="FullLoad";
                    else
                        requirement="PartLoad";

                    helper.showProgress("Submitting...");
                    FormModel model=new FormModel(formBinding.name.getText().toString(), formBinding.date.getText().toString(), "", formBinding.mobile.getText().toString(), formBinding.address.getText().toString(),"", "", formBinding.capweight.getText().toString(), "", formBinding.vehicletype.getText().toString(),"","", "", "", "", "", formBinding.from.getText().toString(), formBinding.to.getText().toString(), requirement);

                    String result=Helper.submitForm(FormDriverLoad.this,"DRIVER_LOAD_Requirement",id,model);

                    if (result.equalsIgnoreCase("submitted")) {
                        helper.stopProgress();
                        onBackPressed();
                    } else
                        helper.stopProgress();

/*
                    helper.showProgress("Submitting...");
                    Con.getToken().insertform("form_driver_load", id, formBinding.name.getText().toString(), formBinding.date.getText().toString(), "", formBinding.mobile.getText().toString(), formBinding.address.getText().toString(),"", "", formBinding.capweight.getText().toString(), "", formBinding.vehicletype.getText().toString(), "", "", "", "", formBinding.from.getText().toString(), formBinding.to.getText().toString(), requirement, new Callback<FormModel>() {
                        @Override
                        public void success(FormModel formModel, Response response) {
                            helper.stopProgress();
                            if(formModel.getStatus().equalsIgnoreCase("true")) {
                                Helper.makeToast(FormDriverLoad.this,"Form Submitted", Toast.LENGTH_SHORT);
                                onBackPressed();
                            }else{
                                Helper.makeToast(FormDriverLoad.this,formModel.getMsg(), Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            helper.stopProgress();
                        }
                    });*/

                }
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
        } else if (formBinding.capweight.getText().toString().isEmpty()) {
            formBinding.capweight.requestFocus();
            formBinding.capweight.setError("Input Weight");
            return false;
        } else if (formBinding.from.getText().toString().isEmpty()) {
            formBinding.from.requestFocus();
            formBinding.from.setError("Input Source");
            return false;
        } else if (formBinding.to.getText().toString().isEmpty()) {
            formBinding.to.requestFocus();
            formBinding.to.setError("Input Destination");
            return false;
        } else
            return true;
    }


    public void showDate(View bindview) {
        DatePickerDialog datePickerDialog = Helper.getDatePickerDialog(this);
        datePickerDialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
            Helper.setDate(formBinding.date,dayOfMonth,month,year);
        });

        datePickerDialog.show();
    }

}