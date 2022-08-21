package com.myapp.lovetest_azuredragon3000.congiap12;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.boitinhyeu.ModelCungMang;
import com.myapp.mylibrary.boitinhyeu.PhuongTay;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCungMang extends HandleDB {

    private static final String TABLE_CUNG_MANG = "tbl_cung_mang";
    private static final String TABLE_BOI_PHUONG_DONG = "boi_phuong_dong";
    private static final String TABLE_BOI_PHUONG_TAY = "boi_phuong_tay";
    private static volatile DatabaseCungMang INSTANCE;

    private DatabaseCungMang(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseCungMang getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseCungMang.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseCungMang(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }


    public List<ModelCungMang> getCungMang() {
        List<ModelCungMang> list = new ArrayList<>();
        openDatabase();

        String strQuery = "SELECT * FROM " + TABLE_CUNG_MANG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ModelCungMang modelCungMang = new ModelCungMang(cursor.getInt(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6));
                list.add(modelCungMang);
            } while (cursor.moveToNext());
        }
        cursor.close();

        closeDatabase();
        return list;
    }



}
