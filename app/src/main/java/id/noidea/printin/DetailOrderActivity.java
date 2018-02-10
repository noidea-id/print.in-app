package id.noidea.printin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import id.noidea.printin.Session.SessionManager;

import static java.security.AccessController.getContext;

public class DetailOrderActivity extends AppCompatActivity {

    TextView nama, nama_pesanan1, harga_pesanan1, nama_pesanan2, harga_pesanan2, total_harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        nama = findViewById(R.id.nama);
        nama_pesanan1 = findViewById(R.id.nama_pesanan1);
        harga_pesanan1 = findViewById(R.id.harga_pesanan1);
        nama_pesanan2 = findViewById(R.id.nama_pesanan2);
        harga_pesanan2 = findViewById(R.id.harga_pesanan2);
        total_harga = findViewById(R.id.total_harga);

        final SessionManager session = new SessionManager(getBaseContext());

        if(getIntent().hasExtra("satu")) {
            String jenis = getIntent().getStringExtra("jenis");
            String lebar = getIntent().getStringExtra("lebar");
            String panjang = getIntent().getStringExtra("panjang");

            Integer lebar_int = Integer.parseInt(lebar);
            Integer panjang_int = Integer.parseInt(panjang);
            Integer hasil = lebar_int * panjang_int * 15000;
            nama.setText(session.getName());
            nama_pesanan1.setText("Ongkos Kirim");
            harga_pesanan1.setText("Rp 5000");
            nama_pesanan2.setText(jenis + "("+ panjang + " x " + lebar + " meter"+")");
            harga_pesanan2.setText("Rp " + hasil);
            Integer total = hasil + 5000;
            total_harga.setText("Rp " + total);

        }else if(getIntent().hasExtra("dua")) {

        }else if(getIntent().hasExtra("tiga")) {

        }

    }
}
