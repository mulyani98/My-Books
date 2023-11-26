package com.mulyani.mybooks.View.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mulyani.mybooks.MainActivity;
import com.mulyani.mybooks.R;

/* Authentication activity */
public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button signInButton;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView signUpView;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_activity);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        signInButton = findViewById(R.id.sign_in_button);
        signUpView = findViewById(R.id.sign_up_button);
        progressDialog = new ProgressDialog(this);
        signInButton.setOnClickListener(this);
        signUpView.setOnClickListener(this);
    }

    private void userLogin() {
        String email = inputEmail.getText().toString().trim();
        String pass = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please input your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please input your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(AuthenticationActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(AuthenticationActivity.this,  "Login Failed. Try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            userLogin();
        }

        if (v == signUpView) {
            finish();
            startActivity(new Intent(this, RegistrationActivity.class));
        }
    }

}
