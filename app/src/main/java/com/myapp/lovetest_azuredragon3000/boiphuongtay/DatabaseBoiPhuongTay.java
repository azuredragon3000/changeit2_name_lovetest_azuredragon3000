package com.myapp.lovetest_azuredragon3000.boiphuongtay;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.boitinhyeu.ModelCungMang;
import com.myapp.mylibrary.boitinhyeu.PhuongDong;
import com.myapp.mylibrary.boitinhyeu.PhuongTay;

import java.util.ArrayList;
import java.util.List;

public class DatabaseBoiPhuongTay extends HandleDB {

    private static final String TABLE_CUNG_MANG = "tbl_cung_mang";
    private static final String TABLE_BOI_PHUONG_DONG = "boi_phuong_dong";
    private static final String TABLE_BOI_PHUONG_TAY = "boi_phuong_tay";
    private static volatile DatabaseBoiPhuongTay INSTANCE;

    private DatabaseBoiPhuongTay(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseBoiPhuongTay getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseBoiPhuongTay.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseBoiPhuongTay(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }


    public PhuongTay getBoiPhuongTay(String id) {
        openDatabase();
        PhuongTay phuongTay = null;
        String strQuery = "SELECT * FROM " + TABLE_BOI_PHUONG_TAY + " WHERE id = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{id});
        if (cursor.moveToFirst()) {
            do {
                phuongTay = new PhuongTay(cursor.getInt(0), cursor.getString(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return phuongTay;
    }



}
