package com.hpweb.pickerdrive;

import static com.hpweb.pickerdrive.Form.Type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import com.hpweb.pickerdrive.databinding.ActivityFormCompanyVehicleBinding;
import com.model.FormModel;


public class FormCompanyLoad extends AppCompatActivity {

    ActivityFormCompanyVehicleBinding binding;

    String id,requirement;
    Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_company_vehicle);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_form_company_vehicle);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(Type);

        binding.from.setAdapter(Helper.getCities(FormCompanyLoad.this));
        binding.to.setAdapter(Helper.getCities(FormCompanyLoad.this));
        binding.date.setText(Helper.getDate());


        id = Helper.getDataLocal(this, Helper.key_id);
        helper = new Helper(this);

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    if(binding.requirement.getCheckedRadioButtonId()==R.id.fullload)
                        requirement="FullLoad";
                    else
                        requirement="PartLoad";

                    helper.showProgress("Submitting...");
                    FormModel model=new FormModel(binding.name.getText().toString(), binding.date.getText().toString(), "", binding.mobile.getText().toString(), binding.address.getText().toString(), binding.company.getText().toString(), "", binding.capweight.getText().toString(), "", binding.vehicletype.getText().toString(),"","", binding.detail.getText().toString(), binding.weight.getText().toString(), "", "", binding.from.getText().toString(), binding.to.getText().toString(), requirement);

                    String result=Helper.submitForm(FormCompanyLoad.this,"CAR_CARRIER_COMPANY_LOAD_Requirement",id,model);

                    if (result.equalsIgnoreCase("submitted")) {
                        helper.stopProgress();
                        onBackPressed();
                    } else
                        helper.stopProgress();

//                    Con.getToken().insertform("car_carrier_car_load", id, binding.name.getText().toString(), binding.date.getText().toString(), "", binding.mobile.getText().toString(), binding.address.getText().toString(), binding.company.getText().toString(), "", binding.capweight.getText().toString(), "", binding.vehicletype.getText().toString(), binding.detail.getText().toString(), binding.weight.getText().toString(), "", "", binding.from.getText().toString(), binding.to.getText().toString(), requirement, new Callback<FormModel>() {
//                        @Override
//                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                            UserModel formModel=response.body();
//                            if(formModel.getStatus().equalsIgnoreCase("true")) {
//                                Helper.makeToast(FormCompanyLoad.this,"Form Submitted", Toast.LENGTH_SHORT);
//                                onBackPressed();
//                            }else{
//                                Helper.makeToast(FormCompanyLoad.this,formModel.getMsg(), Toast.LENGTH_SHORT);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<UserModel> call, Throwable t) {
//                            helper.stopProgress();
//                            Helper.makeToast(FormCompanyLoad.this, "Try After Sometime", Toast.LENGTH_SHORT);
//
//                        }
//                    });
                }
            }
        });
    }

    private boolean check() {
        if (binding.name.getText().toString().isEmpty() || binding.name.getText().toString().length() < 2) {
            binding.name.requestFocus();
            binding.name.setError("Name Required");
            return false;
        } else if (binding.mobile.getText().toString().isEmpty() || binding.mobile.getText().toString().length() < 9) {
            binding.mobile.requestFocus();
            binding.mobile.setError("Invalid Mobile");
            return false;
        } else if (binding.company.getText().toString().isEmpty() || binding.company.getText().toString().length() < 4) {
            binding.company.requestFocus();
            binding.company.setError("Input Company Name");
            return false;
        } else if (binding.capweight.getText().toString().isEmpty()) {
            binding.capweight.requestFocus();
            binding.capweight.setError("Input Weight");
            return false;
        } else if (binding.from.getText().toString().isEmpty()) {
            binding.from.requestFocus();
            binding.from.setError("Input Source");
            return false;
        } else if (binding.to.getText().toString().isEmpty()) {
            binding.to.requestFocus();
            binding.to.setError("Input Destination");
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
            Helper.setDate(binding.date,d,m,year);
        };

        DatePickerDialog datePickerDialog = Helper.getDatePickerDialog(this, listener);
       /* datePickerDialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
            Helper.setDate(binding.date,dayOfMonth,month,year);
        });*/

        datePickerDialog.show();
    }
}