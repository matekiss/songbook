package cc.mkiss.songbook.ui.login;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.SongbookApplication;

public class LoginActivity
        extends AppCompatActivity
        implements LoginScreen
        , View.OnClickListener {
    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        SongbookApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    @Override
    public void onClick(View v) {
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        loginPresenter.login(
                etEmail.getText().toString(),
                etPassword.getText().toString()
        );
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(R.id.loginLayout), message, Snackbar.LENGTH_INDEFINITE).show();
    }
}
