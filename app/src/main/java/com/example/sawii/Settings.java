package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    LinearLayout keyBasicInfo,keyPaymentInfo,layoutBasicInfo,layoutPaymentInfo;
    TextView minus1,minus2;
    TextView txtSettUsername, txtSettEmail, txtSettPassword, txtSettPhone, txtSettGender, txtSettAge;
    checkingIdentification ch = new checkingIdentification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");


        keyBasicInfo = findViewById(R.id.key_basic_info);
        keyPaymentInfo = findViewById(R.id.key_payment_Info);
        layoutBasicInfo = findViewById(R.id.layout_basic_info);
        layoutPaymentInfo = findViewById(R.id.layout_payment_info);
        minus1 = findViewById(R.id.minus1);
        minus2 = findViewById(R.id.minus2);
//        txtSettUsername = findViewById(R.id.txtSettUsername);
//        txtSettEmail = findViewById(R.id.txtSettEmail);
//        txtSettPassword = findViewById(R.id.txtSettPassword);
//        txtSettGender = findViewById(R.id.txtSettGender);


//        txtSettUsername.setText(ch.username);
//        txtSettEmail.setText(ch.email);
//        txtSettPhone.setText(ch.phone+"");
//        txtSettAge.setText(ch.age+"");
//        String gender = "";
//        if (ch.gender == 1)
//            gender = "Male";
//        else if (ch.gender == 2)
//            gender = "Female";
//        else
//            gender = "null";
//        txtSettGender.setText(gender);




       keyBasicInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (layoutBasicInfo.getVisibility() == View.VISIBLE) {
                   layoutBasicInfo.setVisibility(View.GONE);
                   minus1.setText("+");
               } else {
                   layoutBasicInfo.setVisibility(View.VISIBLE);
                   minus1.setText("-");
               }
           }
       });

        keyPaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutPaymentInfo.getVisibility() == View.VISIBLE) {
                    layoutPaymentInfo.setVisibility(View.GONE);
                    minus2.setText("+");
                }
                else {
                    layoutPaymentInfo.setVisibility(View.VISIBLE);
                    minus2.setText("-");
                }
            }
        });

    }
}