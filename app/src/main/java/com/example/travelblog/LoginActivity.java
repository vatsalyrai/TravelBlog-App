package com.example.travelblog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
      private TextInputLayout UsernameInput;
      private TextInputLayout PasswordInput;
     private ProgressBar progressBar;
      private Button LoginButton;
    private BlogPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new BlogPreferences(this);
        if (preferences.isLoggedIn()) {
            startMainActivity();
            finish();
            return;
        }

        setContentView(R.layout.layout);
        LoginButton = findViewById(R.id.loginButton);
        UsernameInput = findViewById(R.id.textUsernameLayout);
        PasswordInput = findViewById(R.id.textPasswordInput);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.onLoginClicked();
            }
        });
        UsernameInput.getEditText().addTextChangedListener(createTextWatcher(UsernameInput));
        PasswordInput.getEditText().addTextChangedListener(createTextWatcher(PasswordInput));
        progressBar = findViewById(R.id.progressbar);

    }
    private void onLoginClicked()
    {
        String username = UsernameInput.getEditText().getText().toString();
        String password = PasswordInput.getEditText().getText().toString();
        if(username.length()==0)
        {
            UsernameInput.setError("Username field cannot be Empty");
        }
        if(password.length()==0)
        {
            PasswordInput.setError("Password field cannot be empty");
        }
        else if(!username.equals("admin") && !username.equals("admin"))
        {
            showErrorDialog();
        }
        else
        {
            performLogin();
        }
    }
    private TextWatcher createTextWatcher(TextInputLayout Sample)
    {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                     Sample.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    public void showErrorDialog()
        {

              new AlertDialog.Builder(this).setTitle("Login Failed")
                      .setMessage("Username or Password Incorrect. Please try again.")
                      .setPositiveButton("OK",((dialog, which) -> dialog.dismiss()))
                      .show();

        }
        private void performLogin()
        {    UsernameInput.setEnabled(false);
        PasswordInput.setEnabled(false);
            LoginButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            Handler handler = new Handler();
            handler.postDelayed(()->{
                startMainActivity();
                finish();
            },2000);
        }
        public void startMainActivity()
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
}