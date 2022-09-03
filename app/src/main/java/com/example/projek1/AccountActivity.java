package com.example.projek1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser User;
    private DatabaseReference reference;
    private String UserID;
    Button deletebtn,updatebtn;
    FirebaseAuth fa;
    FirebaseUser fu;

    Dialog mydialog;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mydialog = new Dialog(this);

        updatebtn = findViewById(R.id.buttonupdt);
        deletebtn = findViewById(R.id.buttondelete);
        User = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        UserID = User.getUid();
        fu = FirebaseAuth.getInstance().getCurrentUser();
        fa = FirebaseAuth.getInstance();

        final EditText txtnama = (EditText) findViewById(R.id.nametxt);
        final EditText txtemail = (EditText) findViewById(R.id.emailtxt);
        final EditText txtpassword = (EditText) findViewById(R.id.passwordtxt);
        final EditText txtphone = (EditText) findViewById(R.id.phonetxt);
        final EditText txtaddress = (EditText) findViewById(R.id.addrestxt);


        //update
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama     = txtnama.getText().toString();
                String email    = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String phone    = txtphone.getText().toString();
                String address  = txtaddress.getText().toString();

                HashMap map = new HashMap();
                map.put("Address",address);
                map.put("Email",email);
                map.put("Name",nama);
                map.put("Phone",phone);
                map.put("Password",password);

                reference.child(UserID).updateChildren(map).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(AccountActivity.this,"Data berhasil di  upadte",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        //read
        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user information = snapshot.getValue(user.class);
                    if(information != null){
                        String name = information.Name;
                        String email = information.Email;
                        String password = information.Password;
                        String phone = information.Phone;
                        String address = information.Addres;

                        txtnama.setText(name);
                        txtemail.setText(email);
                        txtpassword.setText(password);
                        txtaddress.setText(address);
                        txtphone.setText(phone);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountActivity.this, "ada yang salah", Toast.LENGTH_SHORT).show();

            }
        });

        //delete
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AccountActivity.this);
                dialog.setTitle("apakah anda yakin");
                dialog.setMessage("penghapusan ini dilakukan secara permanen dan tidak bisa mengembalikan akun anda");
                dialog.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fu.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(AccountActivity.this,"Akun delete",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AccountActivity.this,LoginActivity.class);
                                    startActivity(new Intent(AccountActivity.this,signup_Activity.class));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                }
                                else {
                                    Toast.makeText(AccountActivity.this,"akun delete gagal", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Gagal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog =dialog.create();
                alertDialog.show();
            }
        });


        //---------------Hooks-------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        //---------------Tool bar-----------------------
        setSupportActionBar(toolbar);


        //---------------Navigation Drawer menu---------
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_acc);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_acc:
                break;

            case R.id.nav_home:
                Intent intent = new Intent(AccountActivity.this, DashboardActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_drink:
                intent= new Intent(AccountActivity.this, Minuman.class);
                startActivity(intent);
                break;

            case R.id.nav_foods:
                intent = new Intent(AccountActivity.this, Makanan.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}