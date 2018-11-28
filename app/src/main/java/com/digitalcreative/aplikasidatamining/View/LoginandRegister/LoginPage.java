package com.digitalcreative.aplikasidatamining.View.LoginandRegister;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalcreative.aplikasidatamining.BaseActivity;
import com.digitalcreative.aplikasidatamining.Controller.Firebase;
import com.digitalcreative.aplikasidatamining.R;
import com.digitalcreative.aplikasidatamining.View.HomePage.Halaman_Utama;
import com.digitalcreative.aplikasidatamining.View.HomePage.LoadingPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginPage extends Fragment {
    FirebaseAuth firebaseAuth;
    EditText email, pass;
    String getemail, getpass;

    public LoginPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
            firebaseAuth = FirebaseAuth.getInstance();
            email = view.findViewById(R.id.username);
            pass = view.findViewById(R.id.password);

            //Button Register
            final TextView btn_registrasi =  view.findViewById(R.id.btn_register);
            btn_registrasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.container_base, new RegisterPage())
                            .addToBackStack(null).commit();
                }
            });

            //Button Login
            final Button btn_login = view.findViewById(R.id.btn_login);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkemail_instance();

                }
            });
        return view;
    }

    private void checkemail_instance() {
        getemail = email.getText().toString();
        getpass = pass.getText().toString();
        if (getemail.matches("") && getpass.matches("")) {
            Toast.makeText(getActivity(), "Login Gagal - Email atau Password Kosong", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(getemail, getpass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //success login
                        //loadtheFirebase();
                        Log.d(TAG, "signInWithEmail:success");
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container_base, new LoadingPage())
                                .commit();
                    } else {
                        //fail for login
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getActivity(), "Login Gagal - Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void loadtheFirebase() {
        Firebase firebase = new Firebase();
        firebase.loadfirebase(getContext());
    }

}
