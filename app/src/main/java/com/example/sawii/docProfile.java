package com.example.sawii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class docProfile extends AppCompatActivity {
    TextInputEditText txt_price, txt_bio, txt_Spec, txt_year_ex;
    AppCompatSpinner txt_city;
    Button btn_upload_prof_pic, btn_complate_register;
    CircleImageView img_profileDoc;
    ApiInterface apiInterface;
    Uri uri;
    docProfileData dpd = new docProfileData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        txt_price = findViewById(R.id.txt_price);
        txt_bio = findViewById(R.id.txt_bio);
        txt_Spec = findViewById(R.id.txt_Spec);
        txt_year_ex = findViewById(R.id.txt_year_ex);
        txt_city = findViewById(R.id.txt_city);
        btn_upload_prof_pic = findViewById(R.id.btn_upload_prof_pic);
        btn_complate_register = findViewById(R.id.btn_complate_register);
        img_profileDoc = findViewById(R.id.img_profileDoc);


        btn_upload_prof_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCrop();
            }
        });

        btn_complate_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_price.getText().toString().equals("")){
                    Toast.makeText(docProfile.this, "check for your data !", Toast.LENGTH_SHORT).show();
                }
                else {
                    int id = dpd.doc_id;
                    Toast.makeText(docProfile.this, id+"", Toast.LENGTH_SHORT).show();

                    int price = Integer.parseInt(txt_price.getText().toString());
                    String bio = txt_bio.getText().toString();
                    String city = txt_city.getSelectedItem().toString();
                    String specialties = txt_Spec.getText().toString();
                    int year_of_ex = 0;
                    if(!txt_year_ex.getText().toString().equals(""))
                        year_of_ex = Integer.parseInt(txt_year_ex.getText().toString());


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://sawy.000webhostapp.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    apiInterface = retrofit.create(ApiInterface.class);

                    Call<postDocProfile> call = apiInterface.createDocProfile(id, price, bio, city, specialties,year_of_ex);


                    finish();
                    call.enqueue(new Callback<postDocProfile>() {
                        @Override
                        public void onResponse(Call<postDocProfile> call, Response<postDocProfile> response) {
                            Toast.makeText(docProfile.this, response.body()+" | " , Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<postDocProfile> call, Throwable t) {
//                            Toast.makeText(docProfile.this, "error", Toast.LENGTH_SHORT).show();

                        }
                    });


                }

            }
        });

    }

    private void startCrop() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setAspectRatio(1,1)
                .start(this);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                img_profileDoc.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}