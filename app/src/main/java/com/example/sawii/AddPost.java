package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPost extends AppCompatActivity {
    TextInputEditText txtPostContant;
    Button btnAddPost;
    ApiInterface apiInterface;
    checkingIdentification ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        txtPostContant = findViewById(R.id.txtPostContant);
        btnAddPost = findViewById(R.id.btnAddPost);

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPostContant.getText().toString().equals("")){
                    Toast.makeText(AddPost.this, "You can't Create an empty post !", Toast.LENGTH_SHORT).show();
                }
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();

                    int PatId = ch.id;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String PostContant = txtPostContant.getText().toString();

//                    Toast.makeText(AddPost.this, PatId + " " + dtf.format(now) + " " + PostContant, Toast.LENGTH_SHORT).show();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://sawy.000webhostapp.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    apiInterface = retrofit.create(ApiInterface.class);

                    Call<getPosts> call = apiInterface.createPost(PatId,dtf.format(now),PostContant);

                    call.enqueue(new Callback<getPosts>() {
                        @Override
                        public void onResponse(Call<getPosts> call, Response<getPosts> response) {
                            getPosts getPosts = response.body();

                            Toast.makeText(getBaseContext(), "Post is added.." , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(),Navigation.class);
                            startActivity(intent);
//                            finish();
                        }

                        @Override
                        public void onFailure(Call<getPosts> call, Throwable t) {
                            Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }
}