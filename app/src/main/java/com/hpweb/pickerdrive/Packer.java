package com.hpweb.pickerdrive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Packer extends Fragment {

    String[] title = new String[]{
            "History",
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

    int[] icon = new int[]{
            R.drawable.men_history,
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

    DB db;
    RecyclerView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_packer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DB(getContext());
        list = view.findViewById(R.id.list);

        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.list.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        MainAdapter adapter = new MainAdapter(getContext(), title, icon);
        list.setAdapter(adapter);

        if (db.getUser() != null)
            Helper.setDataLocal(getContext(), db.getUser());
    }

}