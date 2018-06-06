package com.example.jojo.forumplanlogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    ImageView logo;

    //private FirebaseAuth firebaseAuth;


    private static int SPLASH_TIME_OUT= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.forumlogo);

        //firebaseAuth = FirebaseAuth.getInstance();

        /**if(firebaseAuth.getCurrentUser() == null){
         finish();
         startActivity(new Intent(this, PlanActivity.class));
         }**/

        //FirebaseUser user = firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(StartActivity.this, PlanActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);



    }




    @Override
    protected void onStart() {
        super.onStart();

    }

}
