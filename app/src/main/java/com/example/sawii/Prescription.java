package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Prescription extends AppCompatActivity {
    Button btnOk, btnSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        getSupportActionBar().setTitle("Rate Call");

        btnOk = findViewById(R.id.btn_ok);
        btnSupport = findViewById(R.id.btn_support);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Support.class);
                startActivity(intent);
            }
        });

    }
}