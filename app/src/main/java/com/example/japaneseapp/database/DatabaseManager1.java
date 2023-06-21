package com.example.japaneseapp.database;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;




import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;




public class DatabaseManager1 {
    private static final String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.japaneseapp/";
    private static final String DATABSE_NAME = "tiengnhat.sqlite";

    private static final String TABLE_NAME = "save";
    private static final String TABLE_NAME_COLUMN_ID = "id";
    private static final String TABLE_NAME_COLUMN_BAI = "bai";
    private static final String TABLE_NAME_TEST = "test";


    private SQLiteDatabase sqLiteDatabase;
    private static final String SQL_GET_SAVE = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_GET_TEST = "SELECT * FROM " + TABLE_NAME_TEST;
    private Context context;


    public DatabaseManager1(Context context) {
        this.context = context;
        copyDatabases();
    }

    private void copyDatabases() {
        new File(DATABASE_PATH).mkdir();
        try {
            File file = new File(DATABASE_PATH + DATABSE_NAME);
            if (file.exists()) return;
            InputStream input = context.getAssets().open(DATABSE_NAME);

            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);
            int len;
            byte buff[] = new byte[1024];
            while ((len = input.read(buff)) > 0) {
                output.write(buff, 0, len);
            }
            output.close();
            input.close();

            Log.i("a", "Copy Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDB() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABSE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDB() {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }



    public boolean inSert(String tableName, String[] columns, String[] dataColumns) {
        //Tung tu Bundle luu tru theo kieu key value
        openDB();
        ContentValues values = new ContentValues();
        for (int i = 0; i < columns.length; i++) {
            values.put(columns[i], dataColumns[i]);
        }
        long results = sqLiteDatabase.insert(tableName, null, values);
        //Dong database
        closeDB();
        //Tra ve gia tri true/false sau khi insert data thanhcong/thatbai
        return results > -1;
    }

    public boolean delete(String tableName, String where, String[] whereArgs) {
        //Tung tu Bundle luu tru theo kieu key value
        openDB();
        int results = sqLiteDatabase.delete(tableName, where, whereArgs);
        //Dong database
        closeDB();
        //Tra ve gia tri true/false sau khi insert data thanhcong/thatbai
        return results > 0;
    }
}

