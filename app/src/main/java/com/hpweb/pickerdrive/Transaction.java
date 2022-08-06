package com.hpweb.pickerdrive;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.connection.Connection;
import com.hpweb.pickerdrive.databinding.ActivityTransactionBinding;
import com.model.TransactionModel;

import java.util.ArrayList;

import retrofit.RetrofitError;

public class Transaction extends AppCompatActivity {

    ActivityTransactionBinding binding;
    Helper helper;
    ArrayList<TransactionModel> data;
    boolean show=true;
    String id;

    String[] title = new String[]{
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

    String[] formid = new String[]{
            "packer_vehicle",
            "PACKERS_CAR_CARRIER_Requirement",
            "COMPANY_VEHICLE_Requirement",
            "TRANSPORTERS_VEHICLE_Requirement",
            "TRANSPORTERS_LOAD_Requirement",
            "DRIVER_LOAD_Requirement",
            "CAR_CARRIER_COMPANY_LOAD_Requirement",
            "CUSTOMER_VEHICLE_Requirement",
            "STORAGE_Requirement",
            "LABOUR_Requirement",
    };

    int[] icon = new int[]{
            R.drawable.men_packer_vehicle,
            R.drawable.men_packer_car_carrier,
            R.drawable.men_company_vehicle,
            R.drawable.men_transport_vehicle,
            R.drawable.men_transort_load,
            R.drawable.men_driver_load,
            R.drawable.men_car_carier_companyload,
            R.drawable.men_cust_vehicle,
            R.drawable.men_storage,
            R.drawable.labour2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        if (getActionBar() != null)
            getActionBar().setTitle("History");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction);
        helper = new Helper(this);

//        setLocalData();

        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter adapter = new MainAdapter(this, title, icon);
        binding.list.setAdapter(adapter);

        binding.city.setAdapter(Helper.getCities(Transaction.this));
        binding.date.setText(Helper.getDate());
    }

//    private void setLocalData() {
//        data = new ArrayList<>();
//        TransactionModel model;
//        model = new TransactionModel("noimage", "02/05/2021", "Kochi,Telangana,201232", "Mathura,Uttar Pradesh,230132");
//        data.add(model);
//        model = new TransactionModel("noimage", "02/07/2021", "Kochi,Telangana,201232", "Mathura,Uttar Pradesh,230132");
//        data.add(model);
//        model = new TransactionModel("noimage", "02/09/2021", "Kochi,Telangana,201232", "Mathura,Uttar Pradesh,230132");
//        data.add(model);
//    }


    public void showDate(View bindview) {
        DatePickerDialog datePickerDialog = Helper.getDatePickerDialog(Transaction.this);
        datePickerDialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
            month=month+1;
            binding.date.setText(year + "-" + month + "-"+dayOfMonth);
        });

        datePickerDialog.show();
    }


    public void showList(int position) {
        id = Helper.getDataLocal(this, Helper.key_id);

        if (check()) {
            helper.showProgress("Fetching....");
            try {
                Connection.getToken().getFormData(id, binding.city.getText().toString(), binding.date.getText().toString(), formid[position], new retrofit.Callback<TransactionModel>() {
                    @Override
                    public void success(TransactionModel transactionModel, retrofit.client.Response response) {
                        helper.stopProgress();
                        if (transactionModel != null)
                            if (transactionModel.getData() == null || transactionModel.getData().size() < 1) {
                                show = false;
                                Helper.makeToast(Transaction.this, "No Records", Toast.LENGTH_SHORT);
                                binding.emptylayout.setVisibility(View.VISIBLE);
                                binding.list.setVisibility(View.GONE);
                            } else {
                                show = true;
                                TransactionAdapter adapter = new TransactionAdapter(Transaction.this, transactionModel.getData());
                                binding.list.setAdapter(adapter);
                                binding.list.setVisibility(View.VISIBLE);
                                binding.emptylayout.setVisibility(View.GONE);

                            }
                        else {
                            show = false;
                            Helper.makeToast(Transaction.this, "No Records", Toast.LENGTH_SHORT);
                            binding.emptylayout.setVisibility(View.VISIBLE);
                            binding.list.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        show = false;
                        helper.stopProgress();
                        Helper.makeToast(Transaction.this, "Try again after sometime..", Toast.LENGTH_SHORT);
                    }
                });
            } catch (Exception e) {
                helper.stopProgress();
                Helper.makeToast(Transaction.this, "Something goes wrong", Toast.LENGTH_SHORT);
                Log.d("TAG", "showList: " + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(show || binding.emptylayout.getVisibility()==View.VISIBLE) {
            MainAdapter adapter = new MainAdapter(this, title, icon);
            binding.list.setAdapter(adapter);
            show=false;
            binding.list.setVisibility(View.VISIBLE);binding.emptylayout.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }
    }

    private boolean check() {
        if (binding.city.getText().toString().isEmpty()) {
            binding.city.setError("Enter City");
            binding.city.requestFocus();
            return false;
        } else
            return true;
    }
}