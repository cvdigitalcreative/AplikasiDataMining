package com.digitalcreative.aplikasidatamining.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalcreative.aplikasidatamining.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cara_Pembayaran extends Fragment {


    public Cara_Pembayaran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cara__pembayaran, container, false);

        return view;
    }

}
