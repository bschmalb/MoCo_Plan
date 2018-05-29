package com.example.jojo.forumplanlogin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlanActivity extends AppCompatActivity implements OnClickListener{


    Button btnEGOG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnEGOG = findViewById(R.id.btnEGOG);
        btnEGOG.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnEGOG:
                if ( btnEGOG.getText().equals("EG")){
                    btnEGOG.setText("OG");
                } else btnEGOG.setText("EG");
        }

    }

}
