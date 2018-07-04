package io.moxd.team9.shoppingMap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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


    //Layout
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

    //Firebase
    private FirebaseAuth firebaseAuth;

    //Geo
    private static final long POINT_RADIUS = 100; // in Meter
    private static final long PROX_ALERT_EXPIRATION = -1;

    //GPS Koordinaten vom Forum Gummersbach
    private static final double POINT_LATITUDE = 51.02459368705052;
    private static final double POINT_LONGITUDE = 7.565631866455078;

    private static final String PROX_ALERT_INTENT =
            "io.moxd.team9.shoppingMap";

    private LocationManager locationManager;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);


        nv = findViewById(R.id.nv1);

        nav_header_email = nv.findViewById(R.id.nav_header_email);
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


        //ActionBar
        mDrawerLayout = findViewById(R.id.DrawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.nav_friends):

                        //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case (R.id.nav_settings):

                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        break;
                }
                return false;
            }
        });


        final View headerview = nv.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));

            }
        });


        if (firebaseAuth.getCurrentUser() != null) {
            System.out.println("Bin doch nicht verwirrt!");
            //finish();
            //FirebaseUser user = firebaseAuth.getCurrentUser();
            //nav_header_email.setText(user.getEmail());

            //nav_header_email.setVisibility(View.VISIBLE);
            //buttonRound.setText("ME");
        }


        //Geo/GPS Push Notification with BroadcastReceiver
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            System.out.println("No Permission");
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.addProximityAlert(
                POINT_LATITUDE, // the latitude of the central point of the alert region
                POINT_LONGITUDE, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityIntentReceiver(), filter);


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
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                    break;
            case (R.id.btnShowAllStores):
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
