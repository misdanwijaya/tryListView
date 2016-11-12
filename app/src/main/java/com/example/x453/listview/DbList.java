package com.example.x453.listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by x453 on 22/10/2016.
 */

public class DbList {

    //class untuk menyimpan record
    public static class List {
        public String nomor;
    }


    private SQLiteDatabase db;
    private final OpenHelper dbHelper;

    public DbList(Context c) {
        dbHelper =  new OpenHelper(c);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertList(String nomor) {
        ContentValues newValues = new ContentValues();
        newValues.put("NOMOR", nomor);
        return db.insert("LIST", null, newValues);
    }

    //ambil data mahasiswa berdasarkan nama
    public List getList(String nomor) {
        Cursor cur = null;
        List M = new List();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "NOMOR"};
        //parameter, akan mengganti ? pada NAMA=?
        String[] param  = {nomor};

        cur = db.query("LIST",cols,"NOMOR=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.nomor = cur.getString(1);
        }

        return M;
    }
}
