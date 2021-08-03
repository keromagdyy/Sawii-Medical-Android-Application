package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class booking extends AppCompatActivity {
    GridView gridView;
    ApiInterface apiInterface;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> bio = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> spec = new ArrayList<String>();
    ArrayList<String> yoe = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BOOKING");

        gridView = findViewById(R.id.gridView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Call<List<postDocProfile>> callDocprof = apiInterface.getDocProfile();

        callDocprof.enqueue(new Callback<List<postDocProfile>>() {
            @Override
            public void onResponse(Call<List<postDocProfile>> call, Response<List<postDocProfile>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<postDocProfile> getDocprofile = response.body();

                int i = 0;
                for (postDocProfile Docprof : getDocprofile) {


                    Call<List<postSignUp>> callGetUserName = apiInterface.getloginUser();

                    callGetUserName.enqueue(new Callback<List<postSignUp>>() {
                        @Override
                        public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                                return;
                            }

                            List<postSignUp> getusername = response.body();

                            int i = 0;
                            for (postSignUp postSignUp : getusername) {

                                if (Docprof.getDoc_id() == postSignUp.getUser_id()){

                                    name.add("Dr. " + postSignUp.getUsername());
                                    bio.add(Docprof.getBio());
                                    price.add(Docprof.getPrice() + " L.E");
                                    city.add(Docprof.getCountry());
                                    spec.add(Docprof.getSpecialties());
                                    yoe.add(Docprof.getYear_of_ex()+"");
                                }

                                i++;
                            }



                            String[] Name = new String[name.size()];
                            String[] Bio  = new String[bio.size()];
                            String[] Price = new String[price.size()];
                            String[] City = new String[price.size()];
                            String[] Spec = new String[price.size()];
                            String[] Yoe  = new String[price.size()];


                            name.toArray(Name);
                            bio .toArray(Bio);
                            price.toArray(Price);
                            city.toArray(City);
                            spec.toArray(Spec);
                            yoe .toArray(Yoe);

                            DocProfileGridViewAdaptor adaptor = new DocProfileGridViewAdaptor(getBaseContext(),Name,Bio,Price);
                            gridView.setAdapter(adaptor);


                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(),profileDoctor.class);
                                    intent.putExtra("name",Name[position]);
                                    intent.putExtra("bio",Bio[position]);
                                    intent.putExtra("price",Price[position]);
                                    intent.putExtra("city",City[position]);
                                    intent.putExtra("spec",Spec[position]);
                                    intent.putExtra("YOE",Yoe[position]);
                                    startActivity(intent);
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<List<postSignUp>> call, Throwable t) {

                        }
                    });

                    i++;
                }

            }

            @Override
            public void onFailure(Call<List<postDocProfile>> call, Throwable t) {

            }
        });



    }
}