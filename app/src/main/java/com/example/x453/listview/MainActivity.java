package com.example.x453.listview;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.x453.listview.R.id.masukan;

public class MainActivity extends AppCompatActivity {
    //konstanta, supaya bisa membedakan antar message
    public final static String EXTRA_MESSAGE = "com.example.x453.listview.app1.MESSAGE";

    //untuk menerima masukan dari activity lain
    private static final int ACT2_REQUEST = 99 ;

    //menggunakan arrayList untuk menyimpan
    //data yang akan ditampilkan
    private ArrayList<String> items = new ArrayList<>();

    //penghubung antara data dengan listview
    ArrayAdapter adapter;

    //penghitung
    int hitung = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //inisiasi isi
        items.add("satu");
        items.add("dua");
        items.add("tiga");
        items.add("empat");
        items.add("lima");





        final ListView listAngka = (ListView) findViewById(R.id.listAngka);


       /*buat adapter
       3 parameter:
          - context:
          - layout listview: disini kita menggunakan yg sudah ada (nantinya bisa custom)
          - datanya: items
       */
        adapter = new ArrayAdapter (this, android.R.layout.simple_expandable_list_item_1, items);

        //set adapter ke listview
        listAngka.setAdapter(adapter);

        listAngka.setClickable(true);
        listAngka.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String isiBaris = (String) listAngka.getItemAtPosition(position);
                String pesan = "Posisi:"+position +"->"+ isiBaris;
                Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //untuk toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

    }

    //untuk menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    //untuk menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mTambah:
                //Toast.makeText(getApplicationContext(), "Tambah", Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(this, Main2Activity.class);
                intent2.putExtra(EXTRA_MESSAGE, "Masukan Data Baru");
                startActivityForResult(intent2,ACT2_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    public void klikButton(View v) {
        //ganti item, maka otomatis tampilkan akan diupdate
       if(hitung == 0) {
           items.set(0, "one");
       }
        if(hitung == 1) {
            items.set(1, "two");
        }
        if(hitung == 2) {
            items.set(2, "three");
        }
        if(hitung == 3){
            items.set(3,"four");
        }
        if(hitung == 4) {
            items.set(4, "five");
        }

        hitung++;

        //tambah item
       //items.add("lima");
        //items.add("enam");

        //JANGAN LUPA refresh listview, agar layar terupdate
        adapter.notifyDataSetChanged();
    }

    //menerima dari activity dua untuk menambahkan data di list view
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // cek request code
        String masukan = data.getStringExtra(EXTRA_MESSAGE);
        if (requestCode == ACT2_REQUEST) {

            //menerima input dari activity 2

            //tampilkan toast
            Toast toast = Toast.makeText(getApplicationContext(), "Data Ditambahkan", Toast.LENGTH_LONG);
            toast.show();

            //masukan ke list view
            items.add(masukan);

            //insert mahasiswa
            DbList db = new DbList(getApplicationContext());
            db.open();
            db.insertList(masukan);

        }

        //ambil data List
        DbList db = new DbList(getApplicationContext());
        DbList.List m = db.getList(masukan);
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setMessage("nama="+m.nomor);
        ad.show();

        //JANGAN LUPA refresh listview, agar layar terupdate
        adapter.notifyDataSetChanged();
    }


    //menambahkan data di list view
    public void klikTambah(View v){

        Intent intent2 = new Intent(this, Main2Activity.class);
        intent2.putExtra(EXTRA_MESSAGE, "Masukan Data Baru");
        startActivityForResult(intent2,ACT2_REQUEST);

    }

}
