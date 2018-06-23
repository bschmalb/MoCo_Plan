package io.moxd.team9.shoppingMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PlanActivity extends AppCompatActivity implements OnClickListener, View.OnTouchListener {



    Button btnEGOG;
    ImageView ivEG;
    ImageView ivOG;

    TextView nav_header_email;
    Button buttonRound;
    Button btnShowAllStores;

    ViewGroup mainLayout;
    PhotoViewAttacher mAttacher;
    PhotoViewAttacher nAttacher;

    private int xDelta;
    private int yDelta;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView nv;


    private FirebaseAuth firebaseAuth;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);


        nv=  findViewById(R.id.nv1);

        nav_header_email= nv.findViewById(R.id.nav_header_email);
        buttonRound = nv.findViewById(R.id.buttonRound);


        btnShowAllStores = findViewById(R.id.btnShowAllStores);
        btnShowAllStores.setOnClickListener(this);

        btnEGOG = findViewById(R.id.btnEGOG);
        btnEGOG.setOnClickListener(this);

        mainLayout = (ViewGroup) findViewById(R.id.mainLayout);
        ivEG = mainLayout.findViewById(R.id.ivEG);
        ivOG = mainLayout.findViewById(R.id.ivOG);


        ivEG.setOnTouchListener(this);
        ivOG.setOnTouchListener(this);


        mAttacher = new PhotoViewAttacher(ivEG);
        nAttacher = new PhotoViewAttacher(ivOG);


        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){
            //finish();
            //FirebaseUser user = firebaseAuth.getCurrentUser();
            //nav_header_email.setText(user.getEmail());

            nav_header_email.setVisibility(View.VISIBLE);
            //buttonRound.setText("ME");
        }

        //ActionBar
        mDrawerLayout = findViewById(R.id.DrawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case(R.id.nav_friends):
                        //finish();
                        //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case (R.id.nav_settings):
                        //finish()
                        //startActivity...
                        break;
                }
                return false;
            }
        });


        final View headerview = nv.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));

            }
        });

    }
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

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case (R.id.btnEGOG):
                if ( btnEGOG.getText().equals(getResources().getString(R.string.eg))){
                    btnEGOG.setText(getResources().getString(R.string.og));
                    ivEG.setVisibility(View.INVISIBLE);
                    ivOG.setVisibility(View.VISIBLE);
                } else {
                    btnEGOG.setText(getResources().getString(R.string.eg));
                    ivEG.setVisibility(View.VISIBLE);
                    ivOG.setVisibility(View.INVISIBLE);
                }
                break;
            case (R.id.buttonRound):
                    finish();
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                    break;
            case (R.id.btnShowAllStores):
                finish();
                startActivity(new Intent(getApplicationContext(), StoreListActivity.class));
                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
