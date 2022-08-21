package com.myapp.lovetest_azuredragon3000.ngontinh;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.boitinhyeu.ModelDanhNgon;
import com.myapp.mylibrary.boitinhyeu.TuVi;

import java.util.ArrayList;
import java.util.List;

public class DatabaseNgonTinh extends HandleDB {

    private static final String TABLE_CUNG_MANG = "tbl_cung_mang";
    private static final String TABLE_BOI_PHUONG_DONG = "boi_phuong_dong";
    private static final String TABLE_BOI_PHUONG_TAY = "boi_phuong_tay";
    private static final String TABLE_NAM_SINH = "td_tuoi";
    private static final String TUOI_NAME = "td_tuoi_name";
    private static final String CONGIAP_ID = "td_congiap_id";
    private static final String MALE = "td_male";
    private static final String TABLE_NOI_DUNG = "td_tuoi_noidung";
    private static final String TUOI_ID = "td_tuoi_id";
    private static volatile DatabaseNgonTinh INSTANCE;
    private static final String TABLE_DANH_NGON = "danhngon";
    private DatabaseNgonTinh(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseNgonTinh getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseNgonTinh.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseNgonTinh(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }


    public List<ModelDanhNgon> getDanhNgon() {
        List<ModelDanhNgon> list = new ArrayList<>();
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE_DANH_NGON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);
        if (cursor.moveToFirst()){
            do {
                list.add(new ModelDanhNgon(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return list;
    }



}
