package id.noidea.printin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class DetailProdukActivity extends AppCompatActivity {

    RelativeLayout btnOrder;
    EditText panjang_txt, lebar_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        btnOrder = findViewById(R.id.btnOrder);
        panjang_txt = findViewById(R.id.panjang_text);
        lebar_txt = findViewById(R.id.lebar_text);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DetailOrderActivity.class);
                intent.putExtra("satu", 1);
                intent.putExtra("jenis", "X - Banner ");
                intent.putExtra("lebar", lebar_txt.getText().toString());
                intent.putExtra("panjang", panjang_txt.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
