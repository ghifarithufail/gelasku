package com.example.projek1;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Navbar extends AppCompatActivity {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);

        //-------------------HOOKS----------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.toolbar);


        // ------------------Tool bar--------------------

        //------------------Navigation Drawer Menu--------


    }
}