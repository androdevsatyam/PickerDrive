package com.hpweb.pickerdrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hpweb.pickerdrive.databinding.ActivityFormBinding;

import java.util.Calendar;

public class Form extends AppCompatActivity {

    static String Type = "";
    ActivityFormBinding formBinding;
    DatePickerDialog dialog;
    static boolean driver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form);
        /*        switch (Type) {
            case "PACKERS VEHICALE":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_packer_vehicle);
                break;
            case "PACKERS CAR CARRIER":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_formpackercar_carrier);
                break;
            case "COMPANY VEHICALE":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_company_vehicle);
                break;
            case "TRANSPORTERS VEHICALE":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_transport_vehicle);
                break;
            case "TRANSPORTES LOAD":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_transport_load);
                break;
            case "DRIVER LOAD":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_driver_load);
                break;
            case "CAR CARRIER COMPANY LAOD":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_company_car_carrier);
                break;
            case "CUSTOMER VEHICALE":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_cust_vehicle);
                break;
            case "STORAGE":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_storage);
                break;
            case "LABOUR":
                formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_labour);
        }
*/

        formBinding.formType.setText(Type);

        prepareForm();

        formBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    Helper.makeToast(Form.this, "Clicked", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private void prepareForm() {
        Intent intent = null;
        switch (Type) {
            case "History":
                intent = new Intent(Form.this, Transaction.class);
                break;
            case "PACKERS VEHICLE Requirement":
                intent = new Intent(Form.this, FormPackerVehicle.class);
                break;
            case "PACKERS CAR CARRIER Requirement":
                intent = new Intent(Form.this, FormPackerCarCarrier.class);
                break;
            case "COMPANY VEHICLE Requirement":
                intent = new Intent(Form.this, FormCompanyLoad.class);
                break;
            case "TRANSPORTERS VEHICLE Requirement":
                intent = new Intent(Form.this, FormTransportVehicle.class);
                break;
            case "TRANSPORTERS LOAD Requirement":
                intent = new Intent(Form.this, FormTransportLoad.class);
                break;
            case "DRIVER LOAD Requirement":
                intent = new Intent(Form.this, FormDriverLoad.class);
                break;
            case "CAR CARRIER COMPANY LOAD Requirement":
                intent = new Intent(Form.this, FormCompanyLoad.class);
                break;
            case "CUSTOMER VEHICLE Requirement":
                intent = new Intent(Form.this, FormCustVehicle.class);
                break;
            case "STORAGE Requirement":
                intent = new Intent(Form.this, FormStorage.class);
                break;
            case "LABOUR Requirement":
                intent = new Intent(Form.this, FormLabour.class);
        }
        startActivity(intent);
        finish();
    }

    public void showDate(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.MyTimePickerDialogTheme, (datePicker, i, i1, i2) -> {
            if (view.getId() == formBinding.date.getId())
                formBinding.date.setText(i2 + "/" + i1 + "/" + i);
            else
                formBinding.loading.setText(i2 + "/" + i1 + "/" + i);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    public boolean check() {
        if (formBinding.name.getText().toString().isEmpty()) {
            formBinding.name.requestFocus();
            formBinding.name.setError("Name Required");
            return false;
        } else if (formBinding.company.getText().toString().isEmpty()) {
            formBinding.company.requestFocus();
            formBinding.company.setError("Company Name");
            return false;
        } else
            return true;
    }
}