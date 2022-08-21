package com.myapp.lovetest_azuredragon3000.reatimeDB;

import com.google.firebase.database.DatabaseReference;

public class FireBaseWriteDB {

    public FireBaseWriteDB(DatabaseReference mDatabase){
        mDatabase.child("cuong").setValue("abc");
    }
}
