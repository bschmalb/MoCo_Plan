package com.example.jojo.forumplanlogin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlanActivity extends AppCompatActivity implements OnClickListener {


    Button btnEGOG;
    ImageView ivEG;

    ViewGroup mainLayout;

    private int xDelta;
    private int yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnEGOG = findViewById(R.id.btnEGOG);
        btnEGOG.setOnClickListener(this);

        mainLayout = (ViewGroup) findViewById(R.id.mainLayout);
        ivEG = mainLayout.findViewById(R.id.ivEG);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(800, 1600);
        ivEG.setLayoutParams(layoutParams);
        ivEG.setOnTouchListener(new ChoiceTouchListener());


        }


        private final class ChoiceTouchListener implements View.OnTouchListener{


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch ( event.getAction() & MotionEvent.ACTION_MASK){

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        xDelta = x- lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;


                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x- xDelta;
                        layoutParams.topMargin = y-yDelta;
                        layoutParams.rightMargin = -2500;
                        layoutParams.bottomMargin = -2500;
                        v.setLayoutParams(layoutParams);
                        break;

                }
                mainLayout.invalidate();
                return true;
            }
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
