package ba.sum.fpmoz.fitfinity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Polja za unos podataka
       // EditText registerFirtsnameTxt = findViewById(R.id.registerFirstnameTxt);
       // EditText registerLastnameTxt = findViewById(R.id.registerLastnameTxt);
        EditText registerEmailTxt = findViewById(R.id.profile1FirstnameTxt);
        EditText registerPasswordTxt = findViewById(R.id.profile1LastnameTxt);
        EditText registerPasswordCnfTxt = findViewById(R.id.profile1EmailTxt);
        // Gumb za registraciju
        Button registerBtn = findViewById(R.id.profile1SaveBtn);
        // Što se događa nakon klika
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dohvaćanje podataka

                String email = registerEmailTxt.getText().toString();
                String password = registerPasswordTxt.getText().toString();
                String passwordCnf = registerPasswordCnfTxt.getText().toString();


                if (!email.equals("") && !password.equals("") && password.equals(passwordCnf)) {
                    mAuth.createUserWithEmailAndPassword (email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                              //  registerFirtsnameTxt.setText("");
                                // registerLastnameTxt.setText("");
                                registerEmailTxt.setText("");
                                registerPasswordTxt.setText("");
                                registerPasswordCnfTxt.setText("");
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Uspješno ste se registirali.",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Greška prilikom unosa ",
                            Toast.LENGTH_LONG
                    ).show();
                }
                Button openRegisterBtn = findViewById(R.id.openRegisterBtn);

                openRegisterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent registerIntent = new Intent(RegisterActivity.this, Profile1Activity.class);
                        startActivity(registerIntent);
                    }
                });

            }
        });
    }
}