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

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Dialog mydialog;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mydialog = new Dialog(this);


        //---------------Hooks-------------------------
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view);
        toolbar= findViewById(R.id.toolbar);


        //---------------Tool bar-----------------------
        setSupportActionBar(toolbar);


        //---------------Navigation Drawer menu---------
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_drink:
                Intent intent = new Intent(DashboardActivity.this, Minuman.class);
                startActivity(intent);
                break;

            case R.id.nav_foods:
                intent = new Intent(DashboardActivity.this, Makanan.class);
                startActivity(intent);
                break;

            case R.id.nav_acc:
                intent = new Intent(DashboardActivity.this, AccountActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void Greentea(View v){
        mydialog.setContentView(R.layout.activity_pop_up_green);
        mydialog.show();
    }

    public void Coffee(View v){
        mydialog.setContentView(R.layout.pop_up_coffee);
        mydialog.show();
    }

    public void tea (View v){
        mydialog.setContentView(R.layout.pop_up_teh);
        mydialog.show();
    }

    public void lemontea(View v){
        mydialog.setContentView(R.layout.pop_up_lemon_tea);
        mydialog.show();
    }

    public void lecitea(View v){
        mydialog.setContentView(R.layout.pop_up_leci_tea);
        mydialog.show();
    }

    public void susu(View v){
        mydialog.setContentView(R.layout.pop_up_susu);
        mydialog.show();
    }
}