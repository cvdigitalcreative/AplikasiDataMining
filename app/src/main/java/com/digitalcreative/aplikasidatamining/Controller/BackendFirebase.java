package com.digitalcreative.aplikasidatamining.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BackendFirebase {

    public void downloadFile(final Context context) throws IOException {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/aplikasidatamining.appspot.com/o/tes.csv?alt=media&token=7f7d59d8-6234-4eab-b11b-955ce65efd8a");


        final File localFile = File.createTempFile("tes", "csv");

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                System.out.println("beryhasil");
                Tools tools=new Tools();
                Log.e("firebase ",";local tem file created  created " +localFile.getAbsolutePath());
                ArrayList<ArrayList> data=tools.load_excel_format_csv(localFile.getAbsolutePath(),",");
                File file = new File(localFile.getAbsolutePath());
                file.delete();
                tools.saveSharedPreferencesLogList(context,data);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                System.out.println("gagak cok");
            }
        });
    }


}
