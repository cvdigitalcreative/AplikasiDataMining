package com.digitalcreative.aplikasidatamining;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.digitalcreative.aplikasidatamining.View.HomePage.Halaman_Utama;
import com.digitalcreative.aplikasidatamining.View.LoginandRegister.LoginPage;
import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //check when user had login
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            Fragment fragment = new Halaman_Utama();
            FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container_base, fragment);
            fragmentTransaction.commit();
        } else {
            Fragment fragment = new LoginPage();
            FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container_base, fragment);
            fragmentTransaction.commit();
        }
    }
}
