package com.example.projek1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class signup_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextname, editTextphone, editTextemail, editTextpassword, editTextaddress;
    FirebaseAuth mAuth;
    TextView log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        editTextname = findViewById(R.id.nameTV);
        editTextemail = findViewById(R.id.emailTV);
        editTextpassword = findViewById(R.id.passwordTV);
        editTextphone = findViewById(R.id.phoneTV);
        editTextaddress = findViewById(R.id.addresTV);

        log = findViewById(R.id.loginpage);

        findViewById(R.id.buttonsignup).setOnClickListener(this);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup_Activity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {

        }
    }

    private void registeruser(){
        String name = editTextname.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String phone = editTextphone.getText().toString().trim();
        String address = editTextaddress.getText().toString().trim();

        if (name.isEmpty()){
            editTextname.setError("name required");
            editTextname.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextemail.setError("email required");
            editTextemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextpassword.setError("password required");
            editTextpassword.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            editTextphone.setError("phone required");
            editTextphone.requestFocus();
            return;
        }
        if (address.isEmpty()){
            editTextaddress.setError("address required");
            editTextaddress.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         user User = new user(
                                 name,
                                 phone,
                                 email,
                                 password,
                                 address
                         );
                         Toast.makeText(getApplicationContext(), "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(signup_Activity.this,LoginActivity.class));
                         FirebaseDatabase.getInstance().getReference("Users")
                                 .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                 .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(signup_Activity.this, "sukses", Toast.LENGTH_LONG).show();
                                }
                             }
                         });
                     }
                     else{
                         Toast.makeText(signup_Activity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();                     }
                    }
                });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonsignup:
                    registeruser();
                    break;
        }
    }
}

