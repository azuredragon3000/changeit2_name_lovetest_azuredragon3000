package com.myapp.lovetest_azuredragon3000.boiphuongdong;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.boitinhyeu.PhuongDong;

public class DatabaseBoiPhuongDong extends HandleDB {

    private static final String TABLE_BOI_PHUONG_DONG = "boi_phuong_dong";
    private static volatile DatabaseBoiPhuongDong INSTANCE;

    private DatabaseBoiPhuongDong(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseBoiPhuongDong getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseBoiPhuongDong.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseBoiPhuongDong(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public PhuongDong getBoiPhuongDong(String id) {
        openDatabase();
        PhuongDong phuongDong = null;

        String strQuery = "SELECT * FROM " + TABLE_BOI_PHUONG_DONG + " WHERE id = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{id});
        if (cursor.moveToFirst()) {
            do {
                phuongDong = new PhuongDong(cursor.getString(1), cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return phuongDong;
    }



}
