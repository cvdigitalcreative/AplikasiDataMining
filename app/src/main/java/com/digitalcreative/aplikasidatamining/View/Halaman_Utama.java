package com.digitalcreative.aplikasidatamining.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitalcreative.aplikasidatamining.Controller.BackendFirebase;
import com.digitalcreative.aplikasidatamining.Controller.Tools;
import com.digitalcreative.aplikasidatamining.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Halaman_Utama extends Fragment {
    Button lacakbtn, updatedatabtn, updateprofilbtn, csbtn, carapembayaranbtn;

    public Halaman_Utama() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Init
        descTheComponent(view);

        //getValue

        //Actions
        buttonClickonListener();

        return view;
    }

    private void buttonClickonListener() {
        lacakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LacakMobil lacakMobil = new LacakMobil();
//                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container_base, lacakMobil);
//                fragmentTransaction.addToBackStack(null).commit();
                Tools tools=new Tools();
                ArrayList<ArrayList> data_=tools.loadSharedPreferencesLogList(view.getContext());
                System.out.println(data_.get(1).get(1));
            }
        });

        updatedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Update_Data update_data = new Update_Data();
//                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container_base, update_data);
//                fragmentTransaction.addToBackStack(null).commit();
                BackendFirebase backendFirebase=new BackendFirebase();
                try {
                    backendFirebase.downloadFile(view.getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        updateprofilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update_Profil update_profil = new Update_Profil();
                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, update_profil);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        csbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Custumer_Service custumer_service = new Custumer_Service();
                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, custumer_service);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        carapembayaranbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cara_Pembayaran cara_pembayaran = new Cara_Pembayaran();
                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, cara_pembayaran);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    private void descTheComponent(View view) {
        //Button
        lacakbtn = view.findViewById(R.id.lacakmobil_menu);
        updatedatabtn = view.findViewById(R.id.updatedata_menu);
        updateprofilbtn = view.findViewById(R.id.updateprofil_menu);
        csbtn = view.findViewById(R.id.customer_service_menu);
        carapembayaranbtn = view.findViewById(R.id.cara_pembayaran_menu);
    }

}
