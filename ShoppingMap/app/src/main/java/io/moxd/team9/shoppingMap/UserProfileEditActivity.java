package io.moxd.team9.shoppingMap;



import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileEditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextFavoriteShop;
    private EditText editTextDesc;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.edittext_name);
        editTextFavoriteShop = findViewById(R.id.edittext_favoriteShop);
        editTextDesc = findViewById(R.id.edittext_desc);


        findViewById(R.id.buttonSave).setOnClickListener(this);
    }

    private boolean validateInputs(String name, String favoriteShop, String desc) {
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (favoriteShop.isEmpty()) {
            editTextFavoriteShop.setError("Favorite Shop required");
            editTextFavoriteShop.requestFocus();
            return true;
        }

        if (desc.isEmpty()) {
            editTextDesc.setError("Description required");
            editTextDesc.requestFocus();
            return true;
        }

        return false;
    }




   @Override
    public void onClick(View v) {
        String name = editTextName.getText().toString().trim();
        String favoriteShop = editTextFavoriteShop.getText().toString().trim();
        String desc = editTextDesc.getText().toString().trim();

                if (!validateInputs(name, favoriteShop, desc)) {

                    CollectionReference dbProfile = db.collection("profile");

                    Profile profile = new Profile(
                            name,
                            favoriteShop,
                            desc
                    );

                    dbProfile.add(profile)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(UserProfileEditActivity.this, "Profile edited", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserProfileEditActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                }
    }
}