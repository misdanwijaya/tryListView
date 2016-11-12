package com.example.x453.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //menangkap pesan dari main activity 1
        //ambil intent
        Intent intent2 = getIntent();
        //ambil datanya
        String pesan = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //tampilkan toast
        Toast t = Toast.makeText(getApplicationContext(),pesan,Toast.LENGTH_LONG);
        t.show();

    }

    public void tambah(View v) {
        //mengambil masukan
        EditText masukan = (EditText) findViewById(R.id.masukan);
        String input = masukan.getText().toString();

        //menghindari data kosong
        /*if(TextUtils.isEmpty(input)) {
            masukan.setError("Masukan data");
            return;
        }*/

        if (input.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        //mengirim isi
        Intent intent2 = getIntent();
        intent2.putExtra(MainActivity.EXTRA_MESSAGE, input);

        setResult(RESULT_OK, intent2);
        finish();

    }
}
