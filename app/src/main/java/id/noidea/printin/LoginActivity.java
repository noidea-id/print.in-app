package id.noidea.printin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import id.noidea.printin.API.Client.ApiUtils;
import id.noidea.printin.API.Interface.InterfaceLogin;
import id.noidea.printin.API.Item.Login.ItemLogin;
import id.noidea.printin.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout btnRegister;
    EditText inputEmail;
    EditText inputPassword;

    SessionManager session;

    Button btnLogin;
    InterfaceLogin login;

    TextView label_toolbar;
    TextView judul;
    TextView tvBtn;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(LoginActivity.this);

        login = ApiUtils.getLogin();

        if (session.isLoggedIn() == TRUE) {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        } else {


            btnRegister = findViewById(R.id.btnRegister);
            inputEmail = findViewById(R.id.input_email);
            inputPassword = findViewById(R.id.input_password);
            btnLogin = findViewById(R.id.btn_login);

            Typeface lato_semibold = Typeface.createFromAsset(getAssets(), "font/lato_semi_bold.ttf");

            label_toolbar = findViewById(R.id.label_toolbar);
            label_toolbar.setTypeface(lato_semibold);
            judul = findViewById(R.id.textView);
            judul.setTypeface(lato_semibold);
            tvBtn = findViewById(R.id.tvBtn);
            tvBtn.setTypeface(lato_semibold);
            tvLogin = findViewById(R.id.btn_login);
            tvLogin.setTypeface(lato_semibold);
            inputEmail.setTypeface(lato_semibold);
            inputPassword.setTypeface(lato_semibold);

            btnRegister = findViewById(R.id.btnRegister);
            inputEmail = findViewById(R.id.input_email);
            inputPassword = findViewById(R.id.input_password);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(null);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String username = inputEmail.getText().toString();
                    String password = inputPassword.getText().toString();
                    doLogin(username, password);
                }
            });
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                    startActivity(intent);
                }
            });
        }
    }

    private void doLogin(final String username, final String password) {
        Call<ItemLogin> call = login.login(username, password);
        call.enqueue(new Callback<ItemLogin>() {
            @Override
            public void onResponse(Call<ItemLogin> call, Response<ItemLogin> response) {
                if(response.isSuccessful()) {
                    ItemLogin itemLogin = response.body();

                    try {

                        Toast.makeText(getBaseContext(),"Anda berhasil masuk",Toast.LENGTH_LONG).show();
                        session.createLoginSession(itemLogin.getUserId(), itemLogin.getName(), itemLogin.getEmail(), itemLogin.getRole(), itemLogin.getAvatar(), itemLogin.getToken().getAccessToken());
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } catch(Exception ex) {
                        Toast.makeText(LoginActivity.this, "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemLogin> call, Throwable t) {
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
