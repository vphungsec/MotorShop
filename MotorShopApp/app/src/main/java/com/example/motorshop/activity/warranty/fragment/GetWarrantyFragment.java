package com.example.motorshop.activity.warranty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.motorshop.activity.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class GetWarrantyFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_warranty, container, false);
    }
}