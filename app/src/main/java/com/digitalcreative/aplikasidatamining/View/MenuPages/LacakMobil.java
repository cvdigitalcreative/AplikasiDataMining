package com.digitalcreative.aplikasidatamining.View.MenuPages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.digitalcreative.aplikasidatamining.R;
import com.digitalcreative.aplikasidatamining.View.HomePage.Halaman_Utama;

/**
 * A simple {@link Fragment} subclass.
 */
public class LacakMobil extends Fragment {
    EditText searchmobil, searchnopol;
    Button search_btn, back_btn;
    String getSearchmobil, getSearchNopol;

    public LacakMobil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lacak_mobil, container, false);

        //Init
        descTheComponent(view);

        //getTheValue
        getTheValue();

        //Actions
        doThesearch();
        goBack();
        return view;
    }

    private void doThesearch() {

    }

    private void getTheValue() {
        getSearchmobil = searchmobil.getText().toString();
        getSearchNopol = searchnopol.getText().toString();
    }

    private void descTheComponent(View view) {
        //EditText
        searchmobil = view.findViewById(R.id.search_mobil);
        searchnopol =  view.findViewById(R.id.search_nopol);

        //Button
        search_btn = view.findViewById(R.id.btn_search);
        back_btn= view.findViewById(R.id.search_back_btn);
    }

    private void goBack() {
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Halaman_Utama halaman_utama = new Halaman_Utama();
                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, halaman_utama);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }
}
