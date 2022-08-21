package com.myapp.lovetest_azuredragon3000.congiap12;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.boitinhyeu.PhuongTay;
import com.myapp.mylibrary.boitinhyeu.TuVi;

import java.util.ArrayList;
import java.util.List;

public class DatabaseNamSinh extends HandleDB {

    private static final String TABLE_CUNG_MANG = "tbl_cung_mang";
    private static final String TABLE_BOI_PHUONG_DONG = "boi_phuong_dong";
    private static final String TABLE_BOI_PHUONG_TAY = "boi_phuong_tay";
    private static final String TABLE_NAM_SINH = "td_tuoi";
    private static final String TUOI_NAME = "td_tuoi_name";
    private static final String CONGIAP_ID = "td_congiap_id";
    private static final String MALE = "td_male";
    private static final String TABLE_NOI_DUNG = "td_tuoi_noidung";
    private static final String TUOI_ID = "td_tuoi_id";
    private static volatile DatabaseNamSinh INSTANCE;

    private DatabaseNamSinh(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseNamSinh getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseNamSinh.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseNamSinh(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }


    public List<TuVi> getNamSinh(String idConGiap) {
        List<TuVi> list = new ArrayList<>();
        openDatabase();

        String strQuery = "SELECT * FROM " + TABLE_NAM_SINH + " WHERE " + CONGIAP_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{idConGiap});
        if (cursor.moveToFirst()) {
            do {
                list.add(new TuVi(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();

        return list;
    }

    public TuVi getTuVi(String tuoiId, String male) {
        TuVi tuVi = null;
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE_NOI_DUNG + " WHERE " + TUOI_ID + " = ? AND " + MALE + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[] {tuoiId, male});
        if (cursor.moveToFirst()) {
            do {
                tuVi = new TuVi(cursor.getString(3), cursor.getString(5), cursor.getString(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return tuVi;
    }



}
