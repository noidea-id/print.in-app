package id.noidea.printin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import id.noidea.printin.API.Client.ApiUtils;
import id.noidea.printin.API.Interface.InterfaceLogin;
import id.noidea.printin.Item.Login.ItemLogin;
import id.noidea.printin.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout btnRegister;
    EditText inputEmail, inputPassword;

    SessionManager session;

    Button btnLogin;
    InterfaceLogin login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(LoginActivity.this);

        if (session.isLoggedIn() == TRUE) {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            login = ApiUtils.getLogin();

            btnRegister = findViewById(R.id.btnRegister);
            inputEmail = findViewById(R.id.input_email);
            inputPassword = findViewById(R.id.input_password);
            btnLogin = findViewById(R.id.btn_login);

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
                        session.createLoginSession(itemLogin.getUserId(), itemLogin.getName(), itemLogin.getEmail(), itemLogin.getRole(), itemLogin.getAvatar(), itemLogin.getToken().getAccessToken());
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } catch(Exception ex) {
                        Log.w("Login Exception:", ex.getMessage());
                        Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
