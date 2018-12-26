package com.xcoders.tourmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private Button btnLogin, btnSignup, btnSignupShow;
    private EditText nameET, emailET, passwordET, confirmPasswordET;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialization();
        onClick();

    }

    private void initialization() {
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignupShow = findViewById(R.id.btnSignupShow);
        nameET = findViewById(R.id.nameSignUpId);
        emailET = findViewById(R.id.emailSignUpId);
        passwordET = findViewById(R.id.passwordSignUpId);
        confirmPasswordET = findViewById(R.id.confirmPasswordSignUpId);
    }

    private void onClick() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Verification();

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Verification() {
        String email, password, confirmPassword;
        final String name = nameET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        confirmPassword = confirmPasswordET.getText().toString();

        if(name.isEmpty()){
            toast("enter your full name");
        }

        else if (email.isEmpty()) {
            toast("enter email");

        } else if (email.isEmpty() || !email.contains("@")) {
            emailET.setError("enter valid email");

        } else if (password.isEmpty() || password.length() < 6) {
            passwordET.setError("min length 6");

        } else if (password.isEmpty() || confirmPassword.isEmpty()) {

            toast("enter password");
        } else if (!password.equals(confirmPassword)) {

            toast("password not matched");
        } else {
            SignUpMethod(email, password);
        }


    }

    private void SignUpMethod(String email, String password) {
        final String name = nameET.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user.updateProfile(profileUpdates);
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();
                        } else {
                            toast("SignUp error " + task.getResult());
                            // Toast.makeText(SignUpActivity.this, "SignUp error "+ task.getResult(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
