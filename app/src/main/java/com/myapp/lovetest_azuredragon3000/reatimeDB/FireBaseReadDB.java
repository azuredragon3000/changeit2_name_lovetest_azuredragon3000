package com.myapp.lovetest_azuredragon3000.reatimeDB;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class FireBaseReadDB {

    public FireBaseReadDB(DatabaseReference mDatabase, Context context){
        mDatabase.child("cuong").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
            }else {
                String t = String.valueOf(task.getResult().getValue());
                Toast.makeText(context,t,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
