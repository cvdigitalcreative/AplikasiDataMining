package com.digitalcreative.aplikasidatamining.View.LoginandRegister;


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

import com.digitalcreative.aplikasidatamining.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterPage extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    EditText regUsername, regEmail, regNama, regAlamat, regPassword;
    Button btn_register;
    TextView btn_login;
    String getUsername, getEmail, getNama, getAlamat, getPassword, getCurrentTanggal;

    public RegisterPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_page, container, false);

        //init the variable
        sayHelloboy(view);

        //SetValue
        getCurrentTanggal = getCurrentDate();

        //Actions
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to signup.");

                getUsername = regUsername.getText().toString().trim();
                getEmail = regEmail.getText().toString().trim();
                getNama = regNama.getText().toString();
                getAlamat = regAlamat.getText().toString();
                getPassword = regPassword.getText().toString().trim();

                //check if Edittext not-null
                if (!isEmpty(getUsername) && !isEmpty(getEmail) && !isEmpty(getNama) && !isEmpty(getAlamat) && !isEmpty(getPassword)) {
                    //Insert Information
                    startRegister(getUsername, getEmail, getNama, getAlamat, getPassword);
                } else {
                    Toast.makeText(getActivity(), "Periksa Jika Masih Ada Yang Kosong", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectScreen();
            }
        });

        return view;
    }

    private void startRegister(final String getUsername, final String getEmail, final String getNama, final String getAlamat, final String getPassword) {
        firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    myRef = firebaseDatabase.getReference().child("Users").child(user.getUid());
                    myRef.child("username").setValue(getUsername);
                    myRef.child("password").setValue(getPassword);
                    myRef.child("nama_lengkap").setValue(getNama);
                    myRef.child("no_telepon").setValue(getAlamat);
                    myRef.child("email").setValue(getEmail);
                    myRef.child("status_app").setValue("");
                    myRef.child("status_pembayaran").setValue("");
                    myRef.child("tanggal_aktif").setValue(getCurrentTanggal);
                    myRef.child("tanggal_berakhir").setValue("");

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getActivity(), "Email Telah Terdaftar", Toast.LENGTH_LONG).show();
                }
            }
        });
        redirectScreen();
    }

    private void sayHelloboy(View view) {
        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Variable
        regUsername = view.findViewById(R.id.input_username);
        regEmail = view.findViewById(R.id.input_email);
        regNama = view.findViewById(R.id.input_nama);
        regAlamat = view.findViewById(R.id.input_notel);
        regPassword = view.findViewById(R.id.input_password);

        //Button
        btn_register = view.findViewById(R.id.btn_register2);
        btn_login = view.findViewById(R.id.btn_login2);
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }

    private String getCurrentDate(){
        String date;

        SimpleDateFormat curFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = Calendar.getInstance().getTime();
        date = curFormat.format(dateobj);


        System.out.println("Tanggal Berapa Ini?" +date);
        return date;
    }

    private void redirectScreen() {
        Log.d(TAG, "Redirecting to login screen.");
        Toast.makeText(getActivity(), "Pendaftaran Berhasil.", Toast.LENGTH_LONG).show();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_base, new LoginPage())
                .addToBackStack(null).commit();
    }
}
