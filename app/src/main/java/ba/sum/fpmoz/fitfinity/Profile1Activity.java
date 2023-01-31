
package ba.sum.fpmoz.fitfinity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.fitfinity.model.UserProfile1;

public class Profile1Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://fitfinity-5cb5b-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        EditText profile1UsernameTxt = findViewById(R.id.profile1UsernameTxt);
        EditText profile1FirstnameTxt = findViewById(R.id.profile1FirstnameTxt);
        EditText profile1LastnameTxt = findViewById(R.id.profile1LastnameTxt);
        EditText profile1EmailTxt = findViewById(R.id.profile1EmailTxt);
        Button profile1SaveBtn = findViewById(R.id.profile1SaveBtn);

        if (currentUser != null) {
            DatabaseReference profileRef = mDatabase.getReference("profile").child(currentUser.getUid());
            profile1EmailTxt.setText(currentUser.getEmail());

            profileRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    UserProfile1 profile = task.getResult().getValue(UserProfile1.class);
                    if (profile != null) {
                        profile1FirstnameTxt.setText(profile.getFirstname());
                        profile1LastnameTxt.setText(profile.getLastname());

                    }
                }
            });


            profile1SaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String firstname = profile1FirstnameTxt.getText().toString();
                    String lastname = profile1LastnameTxt.getText().toString();
                    String email = profile1EmailTxt.getText().toString();

                }
            });

            bottomNavigationView = findViewById(R.id.bottom_navigator);
            bottomNavigationView.setSelectedItemId(R.id.profile);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(), ReceiptsActivity.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.search:
                            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.favorites:
                            startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.profile:
                            startActivity(new Intent(getApplicationContext(), Profile1Activity.class));
                            overridePendingTransition(0,0);
                            return true;

                    }
                    return false;
                }
            });
    }
}}