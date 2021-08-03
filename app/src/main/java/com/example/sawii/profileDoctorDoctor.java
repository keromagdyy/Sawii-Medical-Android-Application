package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class profileDoctorDoctor extends AppCompatActivity {
    Button btnBooking;
    LinearLayout keyDoctorInfo,keyReview,layoutDoctorInfo,layoutReview;
    TextView minus1,minus2;
    TextView txtUsername, txtPrice, txtBio, txtCity, txtSpec, txtYOE;
    docProfileData dpd = new docProfileData();
    checkingIdentification ch = new checkingIdentification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor_doctor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");


        btnBooking = findViewById(R.id.btn_booking);
        keyDoctorInfo = findViewById(R.id.key_doctor_info);
        keyReview = findViewById(R.id.key_reviews);
        layoutDoctorInfo = findViewById(R.id.layout_doctor_info);
        layoutReview = findViewById(R.id.layout_reviews);
        minus1 = findViewById(R.id.minus1);
        minus2 = findViewById(R.id.minus2);
        txtUsername = findViewById(R.id.txtUsername);
        txtPrice = findViewById(R.id.txtPrice);
        txtBio = findViewById(R.id.txtBio);
        txtCity = findViewById(R.id.txtCity);
        txtSpec = findViewById(R.id.txtSpec);
        txtYOE = findViewById(R.id.txtYOE);


        Toast.makeText(this, dpd.bio, Toast.LENGTH_SHORT).show();
        txtUsername.setText("Dr. " + ch.username);
        txtPrice.setText(dpd.price+" L.E/Session");
        txtBio.setText(dpd.bio);
        txtCity.setText(dpd.country);
        txtSpec.setText(dpd.specialties);
        txtYOE.setText(dpd.year_of_ex+" Year");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FinalBookDoctor.class);
                startActivity(intent);
            }
        });


        keyDoctorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDoctorInfo.getVisibility() == View.VISIBLE) {
                    layoutDoctorInfo.setVisibility(View.GONE);
                    minus1.setText("+");
                } else {
                    layoutDoctorInfo.setVisibility(View.VISIBLE);
                    minus1.setText("-");
                }
            }
        });

        keyReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutReview.getVisibility() == View.VISIBLE) {
                    layoutReview.setVisibility(View.GONE);
                    minus2.setText("+");
                }
                else {
                    layoutReview.setVisibility(View.VISIBLE);
                    minus2.setText("-");
                }
            }
        });

    }
}