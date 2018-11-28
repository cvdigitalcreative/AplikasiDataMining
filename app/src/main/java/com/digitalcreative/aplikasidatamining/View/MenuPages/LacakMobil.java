package com.digitalcreative.aplikasidatamining.View.MenuPages;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.digitalcreative.aplikasidatamining.Controller.Riwayat_RecyclerView;
import com.digitalcreative.aplikasidatamining.Controller.Tools;
import com.digitalcreative.aplikasidatamining.Model.Model_LacakMobil;
import com.digitalcreative.aplikasidatamining.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LacakMobil extends Fragment {
    EditText searchmobil, searchnopol;
    Button search_btn;
    String getSearchmobil = null , getSearchNopol = null;
    LinearLayout secondLinear, linearnotfound;
    RecyclerView recyclerView;
    Riwayat_RecyclerView riwayat_recyclerView;
    List<Model_LacakMobil> list = new ArrayList<>();


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

        //Actions
        doThesearch();

        //goBack();


        return view;
    }


    private void doThesearch() {
        secondLinear.setVisibility(View.VISIBLE);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getTheValue
                getTheValue();

                ArrayList<ArrayList> data_;
                list.clear();
                recyclerView.setVisibility(View.GONE);
                linearnotfound.setVisibility(View.VISIBLE);

                Tools tools=new Tools();
                File rootPath = new File(Environment.getExternalStorageDirectory(), "file_name");
                if(!rootPath.exists()) {
                    rootPath.mkdirs();
                }

                final File localFile = new File(rootPath,"tes.csv");
                data_=tools.load_excel_format_csv(localFile.toString(),",");
                System.out.println(data_.size());
                if (getSearchNopol.matches("") && getSearchmobil.matches("") || getSearchNopol==null && getSearchmobil==null){
                    Toast.makeText(getActivity(), "Pencarian anda Kosong", Toast.LENGTH_LONG).show();
                } else {

                        if (getSearchNopol.matches("")){
                            for(int i = 1; i < data_.size(); i++){
                                if (data_.get(i).toString().toLowerCase().contains(getSearchmobil.toLowerCase())){
                                    insertData(data_.get(i));
                                }
                            }
                        } else if (getSearchmobil.matches("")){
                            for(int i = 1; i < data_.size(); i++){
                                if (data_.get(i).toString().toLowerCase().contains(getSearchNopol.toLowerCase())){
                                    insertData(data_.get(i));
                                }
                            }
                        } else {
                                for(int i = 1; i < data_.size(); i++){
                                    if (data_.get(i).toString().toLowerCase().contains(getSearchmobil.toLowerCase())) {
                                        insertData(data_.get(i));
                                    } else if (data_.get(i).toString().toLowerCase().contains(getSearchNopol.toLowerCase())) {
                                        insertData(data_.get(i));
                                        }
                                    }
                                }
                            }
                        }
                    });
                        }

    private void insertData(  ArrayList<String>  data_) {
       String [] split_data=data_.get(0).split(";");

          Model_LacakMobil model =  new Model_LacakMobil();
            model.setNama(split_data[1]);
            model.setNo_plat(split_data[2]);
            model.setNama_mobil(split_data[3]);
            if(split_data.length>8){
                model.setCabang(split_data[9]);
            }else{
                model.setCabang("-");
            }


          list.add(model);
          recyclerView.setAdapter(riwayat_recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            linearnotfound.setVisibility(View.GONE);
    }

    private void getTheValue() {
        if (!searchmobil.getText().toString().matches("") || !searchnopol.getText().toString().matches("" ) && !(searchmobil == null) || !(searchnopol ==null)){
            getSearchmobil = searchmobil.getText().toString();
            getSearchNopol = searchnopol.getText().toString();
        } else {
            Toast.makeText(getActivity(), "Pencarian anda Kosong", Toast.LENGTH_LONG).show();
        }

    }

    private void descTheComponent(View view) {
        //EditText
        searchmobil = view.findViewById(R.id.search_mobil);
        searchnopol =  view.findViewById(R.id.search_nopol);

        //Button
        search_btn = view.findViewById(R.id.btn_search);
        //back_btn= view.findViewById(R.id.search_back_btn);

        //Linear Layout
        secondLinear = view.findViewById(R.id.noplat_dan_cari);
        linearnotfound = view.findViewById(R.id.lineargone);


        //RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        riwayat_recyclerView = new Riwayat_RecyclerView(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void goBack() {
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Halaman_Utama halaman_utama = new Halaman_Utama();
//                FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container_base, halaman_utama);
//                fragmentTransaction.addToBackStack(null).commit();
//            }
//        });
    }
}
