package com.digitalcreative.aplikasidatamining.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.digitalcreative.aplikasidatamining.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class BackendFirebase {
    Context context;
    View view;
    LinearLayout linearLayout;

    public BackendFirebase(Context context, View view, LinearLayout finished) {
        this.context = context;
        this.view =  view;
        this.linearLayout = finished;
    }

    public void downloadFile() throws IOException {
        final ProgressDialog progress;
        progress=new ProgressDialog(context);
        progress.setMessage("Updating Data . . .");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/aplikasidatamining.appspot.com/o/tes.csv?alt=media&token=7f7d59d8-6234-4eab-b11b-955ce65efd8a");

        //final File localFile = File.createTempFile("tes", "csv");

        File rootPath = new File(Environment.getExternalStorageDirectory(), "file_name");
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath,"tes.csv");
        localFile.delete();
        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("SUCCEED", "Data Berhasil di Download" +localFile.toString());
                progress.dismiss();
                showUpdateDone();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                System.out.println("gagak cok");
            }
        });

    }

    private void showUpdateDone() {
        //show
        linearLayout.setVisibility(View.VISIBLE);

        //action
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
            }
        });
    }
}
