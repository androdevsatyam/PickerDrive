package com.hpweb.pickerdrive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Home extends Fragment {

    int[] images = new int[]{
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3,
    };

    SliderView slider;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slider=view.findViewById(R.id.imageSlider);

        SliderAdapterExample example=new SliderAdapterExample(getContext(),images);
        slider.setSliderAdapter(example);

    }
}