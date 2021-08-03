package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class profileDoctor extends AppCompatActivity {

    Button btnBook,btnChat;
    LinearLayout keyDoctorInfo,keyReview,layoutDoctorInfo,layoutReview;
    TextView minus1,minus2;
    TextView txtUsername, txtPrice, txtBio, txtCity, txtSpec, txtYOE;
    String Name = "";
    String Bio = "";
    String Price = "";
    String City = "";
    String Spec = "";
    String YOE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Doctor Profile");


        btnBook = findViewById(R.id.btn_book);
        btnChat = findViewById(R.id.btn_chat);
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Name = extras.getString("name");
            Bio =  extras.getString("bio");
            Price =   extras.getString("price");
            City = extras.getString("city");
            Spec = extras.getString("spec");
            YOE =     extras.getString("YOE");
        }

//        Toast.makeText(this, Name + "\n" + Bio + "\n" + Price + "\n" + City + "\n" + Spec + "\n" + YOE, Toast.LENGTH_SHORT).show();


        txtUsername.setText(Name);
        txtPrice.setText(Price);
        txtBio.setText(Bio);
        txtCity.setText(City);
        txtSpec.setText(Spec);
        txtYOE.setText(YOE);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), finalBook.class);
                startActivity(intent);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InsideChat.class);
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