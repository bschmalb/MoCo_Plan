package io.moxd.team9.shoppingMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    ImageView logo;
    private static int SPLASH_TIME_OUT= 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.forumlogo);

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
