package com.digitalcreative.aplikasidatamining.View.MenuPages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalcreative.aplikasidatamining.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Custumer_Service extends Fragment {


    public Custumer_Service() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_custumer_service, container, false);
        return view;
    }

}
