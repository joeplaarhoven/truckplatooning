package com.example.truckplatooningkans352;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truckplatooningkans352.DatabaseHelper.DatabaseHelperTruckInformatie;
import com.example.truckplatooningkans352.GetSet.TruckInformatie;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelperTruckInformatie dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);






        TextView registerBtn = findViewById(R.id.register_registerBTN);
        dbHelper = new DatabaseHelperTruckInformatie(this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText naamET = findViewById(R.id.register_NaamET);
                final String naam = naamET.getText().toString();

                EditText kentekenET = findViewById(R.id.register_KentekenET);
                final String kenteken = kentekenET.getText().toString();

                EditText merkET = findViewById(R.id.register_MerkET);
                final String merk = merkET.getText().toString();

                EditText wachtwoordET = findViewById(R.id.register_WachtwoordET);
                final String wachtwoord = wachtwoordET.getText().toString();

                EditText wachtwoordHerET = findViewById(R.id.register_WachtwoordHerET);
                final String wachtwoordHer = wachtwoordHerET.getText().toString();

                if(wachtwoord.equals(wachtwoordHer)){
                    TruckInformatie tInfo = new TruckInformatie(kenteken, naam, merk, wachtwoord);
                    Boolean bool = dbHelper.addTruckInformatie(tInfo);
                    if(bool){
                        Context context = getApplicationContext();
                        CharSequence text = "U bent geregistreerd!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "Helaas is er iets fout gaat!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }

            }
        });
    }
}
