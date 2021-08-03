package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideChat extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;
    ImageView btnBack;
    EditText txtSendMessage;
    CardView btnUploadPhotos, btnSendMessage;
    ImageView imgRecSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_chat);

        toolbar = findViewById(R.id.toolbar);
        btnBack = findViewById(R.id.btnback);
        textView = findViewById(R.id.title_bar);
        imgRecSend = findViewById(R.id.img_rec_send);
        txtSendMessage = findViewById(R.id.txt_send_message);

        setSupportActionBar(toolbar);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtSendMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(txtSendMessage.getText().toString())) {
                    imgRecSend.setImageResource(R.drawable.send);
                }
                else
                    imgRecSend.setImageResource(R.drawable.mic);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.chat_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}