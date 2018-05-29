package com.example.jojo.forumplanlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true){
            System.out.printf("Schon eingeloggt");
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }


    //Beim Logout: #2 11min
    //UserLocalStore
    //userLocalStore.clearUserData()
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:
                User user = new User(null, null);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);

                startActivity(new Intent (this, PlanActivity.class));

                break;

            case R.id.tvRegisterLink:

                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
