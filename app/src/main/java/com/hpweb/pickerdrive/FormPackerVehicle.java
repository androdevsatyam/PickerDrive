package com.hpweb.pickerdrive;

import static com.hpweb.pickerdrive.Form.Type;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hpweb.pickerdrive.databinding.ActivityFormPackerVehicleBinding;
import com.model.FormModel;

public class FormPackerVehicle extends AppCompatActivity {

    ActivityFormPackerVehicleBinding vehicleBinding;
    String id,requirement,loadtype;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vehicleBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_packer_vehicle);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(Type);

        vehicleBinding.from.setAdapter(Helper.getCities(FormPackerVehicle.this));
        vehicleBinding.to.setAdapter(Helper.getCities(FormPackerVehicle.this));
        vehicleBinding.date.setText(Helper.getDate());

        id = Helper.getDataLocal(this, Helper.key_id);
        helper = new Helper(this);

        vehicleBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    if(vehicleBinding.requirement.getCheckedRadioButtonId()==R.id.fullload)
                        requirement="FullLoad";
                    else
                        requirement="PartLoad";

                    if(vehicleBinding.loadtype.getCheckedRadioButtonId()==R.id.fullload)
                        loadtype="HouseLoad";
                    else
                        loadtype="Commercial";

                    helper.showProgress("Submitting...");

                    FormModel model=new FormModel(vehicleBinding.name.getText().toString(), vehicleBinding.date.getText().toString(), "", vehicleBinding.mobile.getText().toString(), vehicleBinding.address.getText().toString(), vehicleBinding.company.getText().toString(), "", vehicleBinding.capweight.getText().toString(), "", "","","", "", "", "", "", vehicleBinding.from.getText().toString(), vehicleBinding.to.getText().toString(), requirement);

                    String result=Helper.submitForm(FormPackerVehicle.this,"packer_vehicle",id,model);

                    if (result.equalsIgnoreCase("submitted")) {
                        helper.stopProgress();
                        onBackPressed();
                    } else
                        helper.stopProgress();


                   /* helper.showProgress("Submitting...");
                    Con.getToken().insertform("packer_vehicle", id, vehicleBinding.name.getText().toString(), vehicleBinding.date.getText().toString(), "", vehicleBinding.mobile.getText().toString(), vehicleBinding.address.getText().toString(), vehicleBinding.company.getText().toString(), "", vehicleBinding.capweight.getText().toString(), "", "", "", "", "", "", vehicleBinding.from.getText().toString(), vehicleBinding.to.getText().toString(), requirement, new Callback<FormModel>() {
                        @Override
                        public void success(FormModel formModel, Response response) {
                            helper.stopProgress();
                            if(formModel.getStatus().equalsIgnoreCase("true")) {
                                Helper.makeToast(FormPackerVehicle.this,"Form Submitted", Toast.LENGTH_SHORT);
                                onBackPressed();
                            }else{
                                Helper.makeToast(FormPackerVehicle.this,formModel.getMsg(), Toast.LENGTH_SHORT);
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
        if (vehicleBinding.name.getText().toString().isEmpty() || vehicleBinding.name.getText().toString().length() < 2) {
            vehicleBinding.name.requestFocus();
            vehicleBinding.name.setError("Name Required");
            return false;
        } else if (vehicleBinding.mobile.getText().toString().isEmpty() || vehicleBinding.mobile.getText().toString().length() < 9) {
            vehicleBinding.mobile.requestFocus();
            vehicleBinding.mobile.setError("Invalid Mobile");
            return false;
        } else if (vehicleBinding.company.getText().toString().isEmpty() || vehicleBinding.company.getText().toString().length() < 4) {
            vehicleBinding.company.requestFocus();
            vehicleBinding.company.setError("Input Company Name");
            return false;
        } else if (vehicleBinding.capweight.getText().toString().isEmpty()) {
           vehicleBinding.capweight.requestFocus();
            vehicleBinding.capweight.setError("Input Weight");
            return false;
        }else if (vehicleBinding.from.getText().toString().isEmpty()) {
            vehicleBinding.from.requestFocus();
            vehicleBinding.from.setError("Input Source");
            return false;
        } else if (vehicleBinding.to.getText().toString().isEmpty()) {
            vehicleBinding.to.requestFocus();
            vehicleBinding.to.setError("Input Destination");
            return false;
        } else
            return true;
    }

    public void showDate(View bindview) {
        DatePickerDialog datePickerDialog = Helper.getDatePickerDialog(FormPackerVehicle.this);
        datePickerDialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
           Helper.setDate(vehicleBinding.date,dayOfMonth,month,year);
        });

        datePickerDialog.show();
    }
}