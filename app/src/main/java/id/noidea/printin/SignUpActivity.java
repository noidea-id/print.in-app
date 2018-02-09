package id.noidea.printin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    RelativeLayout btnLogin;

    TextView label_toolbar;
    TextView judul;
    TextView tvBtn;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnLogin = findViewById(R.id.btnLogin);

        Typeface lato_semibold = Typeface.createFromAsset(getAssets(), "font/lato_semi_bold.ttf");

        label_toolbar = findViewById(R.id.label_toolbar);
        label_toolbar.setTypeface(lato_semibold);
        judul = findViewById(R.id.textView);
        judul.setTypeface(lato_semibold);
        tvBtn = findViewById(R.id.tvBtn);
        tvBtn.setTypeface(lato_semibold);
        tvSignUp = findViewById(R.id.SignUp);
        tvSignUp.setTypeface(lato_semibold);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
