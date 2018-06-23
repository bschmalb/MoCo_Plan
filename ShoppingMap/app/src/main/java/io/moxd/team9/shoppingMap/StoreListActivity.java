package io.moxd.team9.shoppingMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StoreListActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonBackToMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        buttonBackToMap = findViewById(R.id.buttonBackToMap);
        buttonBackToMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case(R.id.buttonBackToMap):
                finish();
                startActivity(new Intent(getApplicationContext(), PlanActivity.class));
                break;
        }
    }
}
