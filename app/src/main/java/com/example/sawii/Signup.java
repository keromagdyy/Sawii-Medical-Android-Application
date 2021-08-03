package com.example.sawii;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Signup extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CheckBox chDoctor;
    private TextInputEditText txt_fname, txt_lname, txt_email, txt_password, txt_cpassword, txt_phone, txt_age;
    private AppCompatSpinner txt_gender;
    private TextView txtEnterCert;
    private LinearLayout layoutUpload;
    private Button btnSignUp;
    docProfileData dpd = new docProfileData();
    CircleImageView img_profileDoc;
    Button btnUploadFile;


    ApiInterface apiInterface;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Signup() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);


        chDoctor = view.findViewById(R.id.chDoctor);
        txtEnterCert = view.findViewById(R.id.txtEnterCert);
        layoutUpload = view.findViewById(R.id.layoutUpload);

        txt_fname = view.findViewById(R.id.txt_fname);
        txt_lname = view.findViewById(R.id.txt_lname);
        txt_email = view.findViewById(R.id.txt_email);
        txt_password = view.findViewById(R.id.txt_password);
        txt_cpassword = view.findViewById(R.id.txt_cpassword);
//        txt_phone = view.findViewById(R.id.txt_phone);
        txt_gender = view.findViewById(R.id.txt_gender);
//        txt_age = view.findViewById(R.id.txt_age);
        btnSignUp = view.findViewById(R.id.btn_signup);
        btnUploadFile = view.findViewById(R.id.btnUploadFile);



        chDoctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chDoctor.isChecked()) {
                    txtEnterCert.setVisibility(View.VISIBLE);
                    layoutUpload.setVisibility(View.VISIBLE);
                }
                else {
                    txtEnterCert.setVisibility(View.GONE);
                    layoutUpload.setVisibility(View.GONE);
                }
            }
        });

        btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCrop();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (txt_fname.getText().toString().equals("")
                        || txt_lname.getText().toString().equals("")
                        || txt_email.getText().toString().equals("")
                        || txt_password.getText().toString().equals("")
                        || txt_cpassword.getText().toString().equals("")){

                    Toast.makeText(getContext(), "check for your data !", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String username = txt_fname.getText().toString() + " " + txt_lname.getText().toString();
                    String email = txt_email.getText().toString();
                    String password = txt_password.getText().toString();
                    String cpassword = txt_cpassword.getText().toString();
                    String gender = txt_gender.getSelectedItem().toString();

                    int int_gender = 0;
                    if (gender == "male")
                        int_gender = 1;
                    else
                        int_gender = 2;


                    if (password.equals(cpassword)){

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://sawy.000webhostapp.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        apiInterface = retrofit.create(ApiInterface.class);

                        Call<List<postSignUp>> callGetUser = apiInterface.getloginUser();

                        int finalInt_gender = int_gender;
                        callGetUser.enqueue(new Callback<List<postSignUp>>() {
                            @Override
                            public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(getContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                                    return;
                                }

                                List<postSignUp> getlog1 = response.body();

                                int i = 0;
                                for (postSignUp postSignUp : getlog1) {

                                    if (postSignUp.getUser_email().equals(email)) {

                                        Toast.makeText(getContext(), "Error: This Account is already exist", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    i++;
                                }
                                Toast.makeText(getContext(), "Signup Succesfully !!", Toast.LENGTH_SHORT).show();
                                if (chDoctor.isChecked()){
                                    Call<postSignUp> call1 = apiInterface.createSignUpUser(1, username, email, password, finalInt_gender, "");

                                    call1.enqueue(new Callback<postSignUp>() {
                                        @Override
                                        public void onResponse(Call<postSignUp> call, Response<postSignUp> response) {

                                            postSignUp postSignUp = response.body();

                                            dpd.doc_id = postSignUp.getUser_id();
                                            Intent intent = new Intent(getContext(),docProfile.class);
                                            startActivity(intent);

                                        }

                                        @Override
                                        public void onFailure(Call<postSignUp> call, Throwable t) {
                                            Toast.makeText(getContext(),  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else {
                                    Call<postSignUp> call2 = apiInterface.createSignUpUser(0, username, email, password, finalInt_gender, "");

                                    call2.enqueue(new Callback<postSignUp>() {
                                        @Override
                                        public void onResponse(Call<postSignUp> call, Response<postSignUp> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<postSignUp> call, Throwable t) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<List<postSignUp>> call, Throwable t) {

                            }
                        });
                    }
                    else
                        Toast.makeText(getContext(), "Thw password is not confirmed", Toast.LENGTH_SHORT).show();



                }
            }
        });

        return view;
    }


    private void startCrop() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setAspectRatio(1,1)
                .start(getActivity());

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Uri resultUri = result.getUri();
                img_profileDoc.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }



}