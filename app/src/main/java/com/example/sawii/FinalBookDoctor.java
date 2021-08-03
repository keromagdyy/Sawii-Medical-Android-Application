package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FinalBookDoctor extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    CalendarView calendar;
    TextView txtDate;
    Button btnAddSession;
    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_book_doctor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BOOKING");

        calendar = findViewById(R.id.calender);
        txtDate = findViewById(R.id.txtDate);
        btnAddSession = findViewById(R.id.btn_add_session);

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


        btnAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new com.example.sawii.TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        txtTime = findViewById(R.id.txt_time);
        txtTime.setText(hourOfDay + " : " + minute);
    }
}