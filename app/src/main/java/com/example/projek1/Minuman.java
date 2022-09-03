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

public class Minuman extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Dialog mydialog;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minuman);
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

        navigationView.setCheckedItem(R.id.nav_drink);
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
                Intent intent = new Intent(Minuman.this, DashboardActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_drink:
                break;

            case R.id.nav_foods:
                intent = new Intent(Minuman.this, Makanan.class);
                startActivity(intent);
                break;

            case R.id.nav_acc:
                intent = new Intent(Minuman.this, AccountActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void tehhijau(View v){
        mydialog.setContentView(R.layout.pop_up_green_tea);
        mydialog.show();
    }
    public void lyechee(View v){
        mydialog.setContentView(R.layout.pop_up_leci_tea_drinks);
        mydialog.show();
    }
    public void vanilallate(View v){
        mydialog.setContentView(R.layout.pop_up_vanilalatte_drinks);
        mydialog.show();
    }
    public void lemon(View v){
        mydialog.setContentView(R.layout.pop_up_lemon_tea_drinks);
        mydialog.show();
    }
    public void americano(View v){
        mydialog.setContentView(R.layout.pop_up_americano_drinks);
        mydialog.show();
    }
    public void blue(View v){
        mydialog.setContentView(R.layout.pop_up_bluemoctail_drinks);
        mydialog.show();
    }

}