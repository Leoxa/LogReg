package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper {

    private static final int DBversion = 1;
    private static final String DBname = "felhasznalok.db";

    private static final String Table_Name = "felhasznalok";

    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHNEV = "felhnev";
    private static final String COL_JELSZO = "jelszo";
    private static final String COL_TELJESNEV = "teljesnev";


    public Connection(Context context){
        super(context,DBname,null,DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTables = "CREATE TABLE IF NOT EXISTS " + Table_Name
                + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " VARCHAR(50), " +
                COL_FELHNEV + " VARCHAR(30), " +
                COL_JELSZO + " VARCHAR(30), " +
                COL_TELJESNEV + "VARCHAR)";
        db.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean AdatFelvetel(String email, String uname, String password, String fullname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, uname);
        values.put(COL_JELSZO, password);
        values.put(COL_TELJESNEV, fullname);

        long erintettsorok = db.insert(Table_Name, null, values);

        if(erintettsorok == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean Login(String uname, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = " SELECT " + COL_FELHNEV +
                " FROM " + Table_Name +
                " WHERE " + COL_FELHNEV + " = " + uname;

        String sql2 = " SELECT " + COL_FELHNEV +
                " FROM " + Table_Name +
                " WHERE " + COL_FELHNEV + " = " + uname +
                " AND " + COL_JELSZO + " = " + pwd;

        String sql3 = " SELECT " + COL_EMAIL +
                " FROM " + Table_Name +
                " WHERE " + COL_EMAIL + " = " + uname;

        String sql4 = " SELECT " + COL_EMAIL +
                " FROM " + Table_Name +
                " WHERE " + COL_EMAIL + " = " + uname +
                " AND " + COL_JELSZO + " = " + pwd;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        Cursor cursor1 = getReadableDatabase().rawQuery(sql2, null);
        Cursor cursor2 = getReadableDatabase().rawQuery(sql3, null);
        Cursor cursor3 = getReadableDatabase().rawQuery(sql4, null);

        if (cursor.getCount() > 0) {
            if (cursor1.getCount() > 0) {
                return true;
            } else {

                return false;
            }
        } else if (cursor2.getCount() > 0) {
            if (cursor3.getCount() > 0) {
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    public String adatlekerdezes(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        return (db.rawQuery("SELECT " + COL_TELJESNEV + " FROM " + Table_Name + " WHERE " + COL_FELHNEV + " = " + username, null)).toString();
    }
}
