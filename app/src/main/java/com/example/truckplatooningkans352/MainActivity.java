package com.example.truckplatooningkans352;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String naam;
    String wachtwoord;
    View view;
    DatabaseHelperTruckInformatie dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        dbHelper = new DatabaseHelperTruckInformatie(this);

        Button loginBtn = findViewById(R.id.login_btnLogin);
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText kentekenET = findViewById(R.id.login_etUsername);
                        final String kenteken = kentekenET.getText().toString();

                        EditText wachtwoordET = findViewById(R.id.login_etWachtwoord);
                        final String wachtwoord = wachtwoordET.getText().toString();
                        Boolean loginCheck = dbHelper.checkLogin(kenteken, wachtwoord);
                        if(loginCheck == true){
                            Context context = getApplicationContext();
                            CharSequence text = "Gelukt, u wordt doorgestuurd!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            Intent i = new Intent(v.getContext(), NavigationActivity.class);
                            i.putExtra("kenteken", kenteken);
                            startActivity(i);
                        }
                        else{
                            Context context = getApplicationContext();
                            CharSequence text = "Er is iets helaas fout gegaan!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
//                        Intent intent = new Intent(v.getContext(), ListViewTestActivity.class);
//                        startActivity(intent);

                    }
                });



        TextView registerBtn = findViewById(R.id.login_lblRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(i);

            }
        });
    }
}