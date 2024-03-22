package com.example.day5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText Nama_plgn, kode_barang, jumlah_barang;
    RadioGroup radiogrp;
    Button buttonProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Nama_plgn = findViewById(R.id.Nama);
        kode_barang = findViewById(R.id.kode_brg);
        jumlah_barang = findViewById(R.id.jml_brg);
        radiogrp = findViewById(R.id.radioGroup);
        buttonProcess = findViewById(R.id.btn_process);
        Klik();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    void Klik(){
        buttonProcess.setOnClickListener(v -> {
            String nama = Nama_plgn.getText().toString();
            String kodebrg = kode_barang.getText().toString();
            int jmlbrg = Integer.parseInt(jumlah_barang.getText().toString());
            int radioButton = radiogrp.getCheckedRadioButtonId();
            String tipePelanggan;

            if(Nama_plgn.getText().toString().isEmpty() || kode_barang.getText().toString().isEmpty() || jumlah_barang.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Mohon Pastikan Isi Semua Data", Toast.LENGTH_SHORT).show();
                return;
            }

            if (radioButton == -1){
                Toast.makeText(MainActivity.this, "Mohon Pilih Salah Satu Tipe Pelanggan", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton radioButton1 = findViewById(radioButton);
            tipePelanggan = radioButton1.getText().toString();

            long harga;
            switch (kodebrg){
                case "SGS":
                    harga = 12999999;
                    break;
                case "OAS":
                    harga = 1989123;
                    break;
                case "PCO":
                    harga = 2730551;
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Kode Barang Tidak Valid", Toast.LENGTH_SHORT).show();
                    return;
            }

            long totalHarga = harga * jmlbrg;

            double diskon = 0;

            switch (tipePelanggan){
                case "Gold":
                    diskon = (10.0 / 100) * totalHarga;
                    break;
                case "Silver":
                    diskon = (5.0 / 100) * totalHarga;
                    break;
                case "Biasa":
                    diskon = (2.0 / 100) * totalHarga;
                    break;
            }

            totalHarga -= (long) diskon;

            if (totalHarga > 10000000) {
                totalHarga += 100000;
            } if (totalHarga < 0){
                totalHarga = 0;
            }

            Intent intent = new Intent(MainActivity.this, HasilActivity.class);

            intent.putExtra("nama", nama);
            intent.putExtra("tipePelanggan", tipePelanggan);
            intent.putExtra("kodebrg", kodebrg);
            intent.putExtra("harga", harga);
            intent.putExtra("jmlbrg", jmlbrg);
            intent.putExtra("totalHarga", totalHarga);

            startActivity(intent);
        });
    }
}