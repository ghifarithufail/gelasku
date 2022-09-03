package com.example.projek1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Makanan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Dialog mydialog;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);
        mydialog = new Dialog(this);


        //---------------Hooks-------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        //---------------Tool bar-----------------------
        setSupportActionBar(toolbar);


        //---------------Navigation Drawer menu---------
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_foods);
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
            case R.id.nav_home:
                Intent intent = new Intent(Makanan.this, DashboardActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_drink:
                intent = new Intent(Makanan.this, Minuman.class);
                startActivity(intent);
                break;

            case R.id.nav_foods:
                break;

                case R.id.nav_acc:
                intent = new Intent(Makanan.this, AccountActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void soft(View v){
        mydialog.setContentView(R.layout.pop_up_soft);
        mydialog.show();
    }

    public void makaron(View v){
        mydialog.setContentView(R.layout.pop_up_makaron);
        mydialog.show();
    }

    public void krofel (View v){
        mydialog.setContentView(R.layout.pop_up_krofel);
        mydialog.show();
    }

    public void redv(View v){
        mydialog.setContentView(R.layout.pop_up_redv);
        mydialog.show();
    }

    public void roll(View v){
        mydialog.setContentView(R.layout.pop_up_roll);
        mydialog.show();
    }

    public void ciskek(View v){
        mydialog.setContentView(R.layout.pop_up_ciskek);
        mydialog.show();
    }
}