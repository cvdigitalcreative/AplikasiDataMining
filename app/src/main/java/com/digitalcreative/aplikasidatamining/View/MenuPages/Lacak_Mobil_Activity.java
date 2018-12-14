package com.digitalcreative.aplikasidatamining.View.MenuPages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalcreative.aplikasidatamining.BaseActivity;
import com.digitalcreative.aplikasidatamining.Controller.Detail_lacakMobil;
import com.digitalcreative.aplikasidatamining.Controller.Tools;
import com.digitalcreative.aplikasidatamining.Model.Model_LacakMobil;
import com.digitalcreative.aplikasidatamining.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lacak_Mobil_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Model_LacakMobil> list = new ArrayList<>();
    ArrayList<ArrayList> data_;
    Detail_lacakMobil detaillacakMobil;
    EditText searchmobil;
    String getSearchMobil;
    LinearLayout linearLayout, progress;
    LinearLayoutManager linearmanager;
    int lastIndex, index;
    Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak_mobil);

        //Init
        descTheComponent();

        //Actions
        getData();
        doThesearch();

        //goBack();
        goBack();
    }

    private void getData() {
        Tools tools=new Tools();

        final File localFile = new File(getApplicationContext().getExternalFilesDir(null),"tes.csv");

        data_=tools.load_excel_format_csv(localFile.toString(),",");
    }

    private void getvalue(){
        list.clear();
        if (!searchmobil.getText().toString().matches("")){
            //progress.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            getSearchMobil = searchmobil.getText().toString();
            performSearch();
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Pencarian anda Kosong", Toast.LENGTH_LONG).show();
        }
    }

    private void goBack() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent =  new Intent(getApplicationContext(), BaseActivity.class);
              startActivity(intent);
            }
        });
    }

    private void doThesearch() {
        searchmobil.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Action ID  = 3
                if (actionId == EditorInfo.IME_ACTION_SEARCH){

                    getvalue();
                    lastIndex = 1;
                }
                searchmobil.setText("");
                return false;
            }

        });
    }

    private void descTheComponent() {
        //Search
        searchmobil =  findViewById(R.id.search_mobil_act);

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view_act);
        detaillacakMobil = new Detail_lacakMobil(list, getApplicationContext());

        linearmanager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearmanager);

        //LinearLayout
        linearLayout =  findViewById(R.id.lineargone);
        progress = findViewById(R.id.progres_lacak);
        progress.setClickable(false);

        //Button
        backbutton = findViewById(R.id.lacak_backbutton);
    }

    private void performSearch() {


        searchmobil.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchmobil.getWindowToken(), 0);
//
        execute1stdataSearch(data_, lastIndex);
        recyclerView.setAdapter(detaillacakMobil);
        recyclerView.addOnScrollListener(paginationAdapter);

    }

    RecyclerView.OnScrollListener paginationAdapter = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = linearmanager.getChildCount();
            int totalItemCount = linearmanager.getItemCount();
            final int firstVisibleItemPosition = linearmanager.findFirstVisibleItemPosition();

//
            int lastdata = data_.size()-1;
            if (index <= lastdata){
                if (firstVisibleItemPosition + visibleItemCount >= totalItemCount) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            execute1stdataSearch(data_, lastIndex);
                            detaillacakMobil.notifyItemInserted(firstVisibleItemPosition);
                        }
                    }, 1000);
                }
            }
        }
    };

    private void execute1stdataSearch(ArrayList<ArrayList> data_, int current) {
        int count = 0;
            for (index = current; index < data_.size(); index++) {
                Pattern p = Pattern.compile(getSearchMobil.toLowerCase());
                Matcher m = p.matcher(data_.get(index).toString().toLowerCase());
                if (m.find()) {
                    count++;
                    if (count <= 9){
                        String[] split_data = data_.get(index).get(0).toString().split(";");

                        final Model_LacakMobil model = new Model_LacakMobil();
                        model.setNama(split_data[1]);
                        System.out.println("sayur kool "+data_.get(index).get(0).toString());
                        if (split_data.length > 2) {
                            model.setNo_plat(split_data[2]);
                            model.setNama_mobil(split_data[3]);
                            model.setFinance(split_data[4]);
                            model.setOvd(split_data[5]);
                            if (split_data.length > 7) {
                                model.setNoka(split_data[8]);
                                model.setNosin(split_data[9]);
                                model.setTahun(split_data[10]);
                                model.setCabang(split_data[7]);
                            }

                        } else {
                            model.setNo_plat("-");
                            model.setNama_mobil("-");
                            model.setFinance("-");
                            model.setOvd("-");
                            model.setNoka("-");
                            model.setNosin("-");
                            model.setTahun("-");
                            model.setCabang("-");
                        }
                        list.add(model);
                    } else {
                        lastIndex = index;
                        //progress.setVisibility(View.GONE);
                        break;
                    }
                }
            }
        }

    }




