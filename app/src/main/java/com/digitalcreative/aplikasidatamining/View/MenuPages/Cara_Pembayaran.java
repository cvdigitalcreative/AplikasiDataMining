package com.digitalcreative.aplikasidatamining.View.MenuPages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitalcreative.aplikasidatamining.R;
import com.digitalcreative.aplikasidatamining.View.HomePage.Halaman_Utama;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cara_Pembayaran extends Fragment {

    Button back;

    public Cara_Pembayaran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cara__pembayaran, container, false);
            //Init the Variable
            sayHelloboys(view);

            //Actions
            doitBoys();
        return view;
    }

    private void sayHelloboys(View view) {
        back = view.findViewById(R.id.btn_cpa_kembali);
    }

    private void doitBoys() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Halaman_Utama update_data = new Halaman_Utama();
                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, update_data);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }
}
