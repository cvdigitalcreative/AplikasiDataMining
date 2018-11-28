package com.digitalcreative.aplikasidatamining.View.HomePage;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalcreative.aplikasidatamining.Controller.BackendFirebase;
import com.digitalcreative.aplikasidatamining.Controller.Firebase;
import com.digitalcreative.aplikasidatamining.Controller.Tools;
import com.digitalcreative.aplikasidatamining.R;
import com.digitalcreative.aplikasidatamining.View.MenuPages.Cara_Pembayaran;
import com.digitalcreative.aplikasidatamining.View.MenuPages.Custumer_Service;
import com.digitalcreative.aplikasidatamining.View.MenuPages.LacakMobil;
import com.digitalcreative.aplikasidatamining.View.MenuPages.Update_Profil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Halaman_Utama extends Fragment {
    LinearLayout updateprofilbtn, csbtn, carapembayaranbtn, lacakbtn, updatedatabtn;
    TextView nama, notelp, firstchar;
    SharedPreferences myPref;
    String nama_u, notelp_u, first_char;
    LinearLayout finished;

    public Halaman_Utama() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //loadFirebase
        loadtheFirebase();

        //Init
        descTheComponent(view);

        //getValue
        getPref();

        //SetValue
        setTheValue();

        //Actions
        buttonClickonListener();
        return view;
    }

    private void loadtheFirebase() {
        Firebase firebase = new Firebase();
        firebase.loadfirebase(getContext());
    }

    private void setTheValue() {
        firstchar.setText(first_char);
        nama.setText(nama_u);
        notelp.setText(notelp_u);
    }

    private void getPref() {
        nama_u = myPref.getString("nama_lengkap", "no define name");
        notelp_u = myPref.getString("no_telepon", "no define no telp");

        first_char = nama_u.substring(0, 1);
    }

    private void buttonClickonListener() {
        lacakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LacakMobil lacakMobil = new LacakMobil();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, lacakMobil);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

       updatedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //loading Data
                BackendFirebase backendFirebase = new BackendFirebase(getContext(), view, finished);
                try {
                    backendFirebase.downloadFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        updateprofilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update_Profil update_profil = new Update_Profil();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, update_profil);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        csbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Custumer_Service custumer_service = new Custumer_Service();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, custumer_service);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        carapembayaranbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cara_Pembayaran cara_pembayaran = new Cara_Pembayaran();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_base, cara_pembayaran);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    private void descTheComponent(View view) {
        //LinearLayout
        lacakbtn = view.findViewById(R.id.lacakmobil_menu);
        updatedatabtn = view.findViewById(R.id.updatedata_menu);
        updateprofilbtn = view.findViewById(R.id.updateprofil_menu);
        csbtn = view.findViewById(R.id.customer_service_menu);
        carapembayaranbtn = view.findViewById(R.id.cara_pembayaran_menu);

        //SharedPref
        myPref = getContext().getSharedPreferences("detailUser", Context.MODE_PRIVATE);

        //TextView
        nama = view.findViewById(R.id.nama_user);
        notelp = view.findViewById(R.id.no_telp);
        firstchar = view.findViewById(R.id.text_icon);

        //Linear Layout
        finished =  view.findViewById(R.id.finish_progressbar);
    }
}