package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profilePatient extends AppCompatActivity {
    CardView imgCard;
    Button btnSessions;
    TextView txtProfUsername;
    RecyclerView RecyclerView;
    ApiInterface apiInterface;
    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> list3 = new ArrayList<String>();
    int id = 0;
    String name = "";

//    checkingIdentification ch = new checkingIdentification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_patient);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        imgCard = findViewById(R.id.cardimg);
        btnSessions = findViewById(R.id.btn_sessions);
        txtProfUsername = findViewById(R.id.txtProfUsername);
        RecyclerView = findViewById(R.id.Recycler_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            name = extras.getString("name");
        }


        txtProfUsername.setText(name);

        btnSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), sessions.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Call<List<getPosts>> call = apiInterface.getPosts();

        call.enqueue(new Callback<List<getPosts>>() {
            @Override
            public void onResponse(Call<List<getPosts>> call, Response<List<getPosts>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<getPosts> posts = response.body();

                int i=0;
                for (getPosts post : posts) {

                    if (post.getPat_id() == id){

                        list1.add(name);
                        list2.add(post.getPost_date_time());
                        list3.add(post.getPost_content());
                    }

                    i++;
                }
                String[] s1 = new String[list1.size()];
                String[] s2 = new String[list2.size()];
                String[] s3 = new String[list3.size()];


                list1.toArray(s1);
                list2.toArray(s2);
                list3.toArray(s3);

                PostsRecyclerViewAdaptor postAdaptor =  new PostsRecyclerViewAdaptor(getBaseContext(),s1,s2,s3);

                RecyclerView.setAdapter(postAdaptor);
                RecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));


            }

            @Override
            public void onFailure(Call<List<getPosts>> call, Throwable t) {

            }
        });


    }
}