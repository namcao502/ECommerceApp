package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword, editTextName;
    TextView textViewSignIn;
    Button buttonSignUp;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        viewBinding();
        listener();
        //getSupportActionBar().hide();
    }
    public void viewBinding(){
        sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime", true);

        if (isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();

            Intent intent = new Intent(RegistrationActivity.this, OnboardActivity.class);
            startActivity(intent);
            finish();
        }

        auth = FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    public void listener(){

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }
    private void signUp(){

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (name.isEmpty()){
            Toast.makeText(this, "Vui l??ng nh???p t??n c???a b???n!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()){
            Toast.makeText(this, "Vui l??ng nh???p t??n t??i kho???n c???a b???n!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()){
            Toast.makeText(this, "Vui l??ng nh???p m???t kh???u c???a b???n!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "????ng k?? th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "????ng k?? th???t b???i!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}