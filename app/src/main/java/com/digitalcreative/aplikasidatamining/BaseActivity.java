package com.digitalcreative.aplikasidatamining;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.digitalcreative.aplikasidatamining.View.HomePage.Halaman_Utama;
import com.digitalcreative.aplikasidatamining.View.LoginandRegister.LoginPage;
import com.digitalcreative.aplikasidatamining.View.MenuPages.LacakMobil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Fragment fragment = new Halaman_Utama();
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_base, fragment);
        fragmentTransaction.commit();
    }
}
