package com.example.day5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hasil);

        Klik();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void Klik(){
        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String tipePelanggan = intent.getStringExtra("tipePelanggan");
        String kodebrg = intent.getStringExtra("kodebrg");
        long harga = intent.getLongExtra("harga", 0);
        int jmlbrg = intent.getIntExtra("jmlbrg", 0);
        long totalHarga = intent.getLongExtra("totalHarga", 0);

        TextView namaTextView = findViewById(R.id.nama_tv);
        namaTextView.setText("Nama: " + nama);

        TextView tipePelangganTextView = findViewById(R.id.tipePlgn_tv);
        tipePelangganTextView.setText("Tipe Pelanggan: " + tipePelanggan);

        TextView kodebrgTextView = findViewById(R.id.kodeBrg_tv);
        kodebrgTextView.setText("Kode Barang: " + kodebrg);

        TextView hargaTextView = findViewById(R.id.harga_tv);
        hargaTextView.setText("Harga: " + "Rp." + harga);

        TextView jmlbrgTextView = findViewById(R.id.jmlBrg_tv);
        jmlbrgTextView.setText("Jumlah Barang: " + jmlbrg);

        TextView totalHargaTextView = findViewById(R.id.Total_tv);
        totalHargaTextView.setText("Total Harga: " + "Rp." + totalHarga);

        Button share = findViewById(R.id.btn_share);

        share.setOnClickListener(v -> {
            String share2 = "Nama: " + nama + "\n" + "Tipe Pelanggan: " + tipePelanggan + "\n" + "Kode Barang: " + kodebrg + "\n" + "Harga: Rp." + harga + "\n" + "Jumlah Barang: " + jmlbrg + "\n" + "Total Harga: Rp." + totalHarga + "\n";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, share2);
            startActivity(Intent.createChooser(shareIntent, "Bagikan Melalui"));
        });
    }
}