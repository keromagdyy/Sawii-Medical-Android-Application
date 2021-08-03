package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ChipNavigationBar chipNavigation;
    private Fragment fragment=null;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FloatingActionButton btnFlotBooking;
    private NavigationView sideBar;
    private LinearLayout layoutProfile;
    ApiInterface apiInterface;
    docProfileData dpd = new docProfileData();
    int ID = 0;
    String NAME = "";
    User user;

    checkingIdentification ch = new checkingIdentification();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        chipNavigation = findViewById(R.id.chip_navigation);
        drawerLayout = findViewById(R.id.drawer);
        btnFlotBooking = findViewById(R.id.btn_flot_booking);
        sideBar = findViewById(R.id.side_bar);


        View headerView = sideBar.getHeaderView(0);
        TextView txtProfUsername = headerView.findViewById(R.id.txtProfUsername);
        TextView txtProfEmail = headerView.findViewById(R.id.txtProfEmail);


        txtProfUsername.setText(ch.username);
        txtProfEmail.setText(ch.email);

//        if (ch.username.equals("Kerolos Magdy"))
//            user = new User("EgQgHha3l9RK57b7pbFhZs59KdW2","Kerolos Magdy");
//        else if (ch.username.equals("Kero Atya"))
//            user = new User("rx8mzilqLqRuj6cZzwJnv5ucuyr2","Kero Atya");
//        else if (ch.username.equals("mohamed hasan"))
//            user = new User("jCRJ6U52Y3NDDynDCwfY6oO0NG73","mohamed hasan");
//        else if (ch.username.equals("philo foaad"))
//            user = new User("YVBAevQ2JzgtdDI44xCww8MeV1b2","philo foaad");





            btnFlotBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ch.name.equals("doctor")){
                    Intent intent = new Intent(getBaseContext(),FinalBookDoctor.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getBaseContext(), booking.class);
                    startActivity(intent);
                }

            }
        });


        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);



        sideBar.setNavigationItemSelectedListener(this);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_actionbar);

        getSupportActionBar().setDisplayUseLogoEnabled(true);


        chipNavigation.setItemSelected(R.id.home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        chipNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.chat:
                        fragment = new chatRegestFragment();
                        break;
                    case R.id.notification:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.advice:
                        fragment = new AdviceFragment();
                        break;
                    case R.id.favovite:
                        fragment = new FavoriteFragment();
                        break;
                }

                if(fragment!=null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }
            }
        });

        if (ch.name == "doctor"){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://sawy.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);

            Call<List<postDocProfile>> callGetDoc = apiInterface.getDocProfile();

            callGetDoc.enqueue(new Callback<List<postDocProfile>>() {
                @Override
                public void onResponse(Call<List<postDocProfile>> call, Response<List<postDocProfile>> response) {
                    List<postDocProfile> getlog1 = response.body();

                    int i = 0;
                    for (postDocProfile postDocProfile : getlog1) {

                        if (ch.id == postDocProfile.getDoc_id()){

                            dpd.doc_id = postDocProfile.getDoc_id();
                            dpd.price = postDocProfile.getPrice();
                            dpd.bio = postDocProfile.getBio();
                            dpd.country = postDocProfile.getCountry();
                            dpd.specialties = postDocProfile.getSpecialties();
                            dpd.year_of_ex = postDocProfile.getYear_of_ex();


                            return;
                        }

                        i++;
                    }
                }

                @Override
                public void onFailure(Call<List<postDocProfile>> call, Throwable t) {

                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
            return true;


        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.sessions:
                Intent intent = new Intent(getApplicationContext(),sessions.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.profile) {

            if (ch.name.equals("doctor")){
                Intent intent = new Intent(getApplicationContext(), profileDoctorDoctor.class);
                startActivity(intent);
            } else {
                ID = ch.id;
                NAME = ch.username;
                Intent intent = new Intent(getApplicationContext(), profilePatient.class);
                intent.putExtra("id",ID);
                intent.putExtra("name",NAME);
                startActivity(intent);
            }

        }
        else if(id == R.id.settings){
            Intent intent = new Intent(getApplicationContext(),Settings.class);
            startActivity(intent);
        }
        else if (id == R.id.supports) {
            Intent intent = new Intent(getApplicationContext(),Support.class);
            startActivity(intent);
        }
        else if (id == R.id.exit){
            finish();
        }


        drawerLayout.closeDrawers();

        return true;
    }
}