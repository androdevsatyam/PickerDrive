package com.hpweb.pickerdrive;

import static com.hpweb.pickerdrive.Form.Type;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hpweb.pickerdrive.databinding.ActivityFormStorageBinding;
import com.model.FormModel;

public class FormStorage extends AppCompatActivity {

    ActivityFormStorageBinding formBinding;

    String id,requirement;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_storage);

        formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_storage);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(Type);

        formBinding.city.setAdapter(Helper.getCities(FormStorage.this));
        formBinding.date.setText(Helper.getDate());

        id = Helper.getDataLocal(this, Helper.key_id);
        helper = new Helper(this);

        formBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {

                    helper.showProgress("Submitting...");

                    FormModel model=new FormModel(formBinding.name.getText().toString(), formBinding.date.getText().toString(), "", formBinding.mobile.getText().toString(), formBinding.address.getText().toString(), "", "","", "", formBinding.vehicletype.getText().toString(),"","", formBinding.detail.getText().toString(), "", "", "", formBinding.city.getText().toString(), "", "");

                    String result=Helper.submitForm(FormStorage.this,"STORAGE_Requirement",id,model);

                    if(result.equalsIgnoreCase("submitted"))
                        onBackPressed();

//                    helper.showProgress("Submitting...");
//                    Con.getToken().insertform("form_storage", id, formBinding.name.getText().toString(), formBinding.date.getText().toString(), "", formBinding.mobile.getText().toString(), formBinding.address.getText().toString(), "", "","", "", formBinding.vehicletype.getText().toString(), formBinding.detail.getText().toString(), "", "", "", formBinding.city.getText().toString(), "", "", new Callback<FormModel>() {
//                        @Override
//                        public void success(FormModel formModel, Response response) {
//                            helper.stopProgress();
//                            if(formModel.getStatus().equalsIgnoreCase("true")) {
//                                Helper.makeToast(FormStorage.this,"Form Submitted", Toast.LENGTH_SHORT);
//                                onBackPressed();
//                            }else{
//                                Helper.makeToast(FormStorage.this,formModel.getMsg(), Toast.LENGTH_SHORT);
//                            }
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            helper.stopProgress();
//                        }
//                    });
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
        }  else
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