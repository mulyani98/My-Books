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

/* Registration activity */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView signInView;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        registerButton = findViewById(R.id.btn_signup);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        signInView = findViewById(R.id.sign_in_text_view);

        registerButton.setOnClickListener(this);
        signInView.setOnClickListener(this);

    }

    private void registerUser() {
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

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Unable to register. Try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == registerButton) {
            registerUser();
        }

        if (v == signInView) {
            startActivity(new Intent(this, AuthenticationActivity.class));
        }

    }
}
