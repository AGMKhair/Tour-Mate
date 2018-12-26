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

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin,btnSignup,loginBtn;
    private EditText emailET,passwordET;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();
        onClick();



    }

    private void onClick()
    {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Verification();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Verification()
    {
        String email,password;
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        if(email.isEmpty())
        {
            toast("email empty");
        }
        else if(password.isEmpty())
        {
            toast("password empty");

        }else
        {
            signInMethod(email,password);
        }

    }

    private void initialization()
    {
        passwordET = findViewById(R.id.passwordLoginId);
        emailET = findViewById(R.id.emailLoginId);
        loginBtn= findViewById(R.id.loginBTN_Id);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
          goToMain();
        }

    }

    private void goToMain()
    {
        Intent gotoMain = new Intent(LoginActivity.this, MainActivity.class);
        gotoMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(gotoMain);
        //finish();

    }

    private void signInMethod(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            goToMain();

                        } else {
                            toast("Auth Error ");
                        }

                    }
                });
    }


    private void toast(String toast)
    {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

}
