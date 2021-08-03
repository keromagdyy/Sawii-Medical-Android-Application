package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class finalBook extends AppCompatActivity {
    CalendarView calendar;
    TextView txtDate;
    LinearLayout squere1,squere2,squere3,squere4,squere5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Confirm Booking");

        calendar = findViewById(R.id.calender);
        txtDate = findViewById(R.id.txtDate);
        squere1 = findViewById(R.id.squere1);
        squere2 = findViewById(R.id.squere2);
        squere3 = findViewById(R.id.squere3);
        squere4 = findViewById(R.id.squere4);
        squere5 = findViewById(R.id.squere5);

        Drawable drawable = squere1.getBackground();


        squere1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(squere1.getBackground() == drawable) {
                    squere1.setBackgroundResource(R.drawable.book_squere2);
                    squere2.setBackground(drawable);
                    squere3.setBackground(drawable);
                    squere4.setBackground(drawable);
                    squere5.setBackground(drawable);
                }
            }
        });

        squere2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(squere2.getBackground() == drawable) {
                    squere2.setBackgroundResource(R.drawable.book_squere2);
                    squere1.setBackground(drawable);
                    squere3.setBackground(drawable);
                    squere4.setBackground(drawable);
                    squere5.setBackground(drawable);
                }
            }
        });

        squere3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(squere3.getBackground() == drawable) {
                    squere3.setBackgroundResource(R.drawable.book_squere2);
                    squere1.setBackground(drawable);
                    squere2.setBackground(drawable);
                    squere4.setBackground(drawable);
                    squere5.setBackground(drawable);
                }
            }
        });

        squere4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(squere4.getBackground() == drawable) {
                    squere4.setBackgroundResource(R.drawable.book_squere2);
                    squere1.setBackground(drawable);
                    squere2.setBackground(drawable);
                    squere3.setBackground(drawable);
                    squere5.setBackground(drawable);
                }
            }
        });

        squere5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(squere5.getBackground() == drawable) {
                    squere5.setBackgroundResource(R.drawable.book_squere2);
                    squere1.setBackground(drawable);
                    squere2.setBackground(drawable);
                    squere3.setBackground(drawable);
                    squere4.setBackground(drawable);
                }
            }
        });



        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Calendar c = Calendar.getInstance();
        String Date = sdf.format(c.getTime());

        txtDate.setText(Date);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "/" + (month+1) + "/" + year;

                txtDate.setText(date);
            }
        });

    }
}