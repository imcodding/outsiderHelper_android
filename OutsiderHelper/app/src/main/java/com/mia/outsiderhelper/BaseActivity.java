package com.mia.outsiderhelper;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public ProgressBar mProgressBar;

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showProgressDialog() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressDialog() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void getStorageUrl(String fileName) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageDownRef = storage.getReferenceFromUrl("gs://outsider-8104a.appspot.com/").child("images").child(fileName);
        storageDownRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()) {
                    Log.d("Storage", task.getResult().toString());
                } else {
                    Log.d("Storage", "Image Download URL load Fail");
                }
            }
        });
    }

    void postStorageImage(String season, ArrayList<String> postValues) {
        getDatabaseReference().child("food").child(season).setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Storage", "Image Update Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Storage", "Image Update Failure");
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}
