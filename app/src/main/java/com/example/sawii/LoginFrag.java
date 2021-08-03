package com.example.sawii;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView txtForgetPassword;
    private Button btnLogin;
    private EditText txtName;
    private EditText txtPass;
    ApiInterface apiInterface;
    boolean chExest = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFrag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFrag newInstance(String param1, String param2) {
        LoginFrag fragment = new LoginFrag();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtForgetPassword = view.findViewById(R.id.txt_forget_password);
        btnLogin = view.findViewById(R.id.btn_login);
        txtName = view.findViewById(R.id.txt_name);
        txtPass = view.findViewById(R.id.txt_pass);

        checkingIdentification ch = new checkingIdentification();



        txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ForgetPassword.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtName.getText().toString();
                String password = txtPass.getText().toString();

                ch.name = "m";



                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://sawy.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiInterface = retrofit.create(ApiInterface.class);

                Call<List<postSignUp>> callGetUser = apiInterface.getloginUser();

                callGetUser.enqueue(new Callback<List<postSignUp>>() {
                    @Override
                    public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<postSignUp> getlog1 = response.body();

                        int i = 0;
                        for (postSignUp postSignUp : getlog1) {

                            if (email.equals(postSignUp.getUser_email())
                             && password.equals(postSignUp.getUser_pass())){

                                Toast.makeText(getContext(), "Login Successfully..!", Toast.LENGTH_SHORT).show();

                                if(postSignUp.getMajor() == 1){
                                    ch.name = "doctor";
                                }

                                ch.id = postSignUp.getUser_id();
                                ch.username = postSignUp.getUsername();
                                ch.email = postSignUp.getUser_email();
                                ch.gender = postSignUp.getUser_gender();

                                Intent intent = new Intent(getContext(),Navigation.class);
                                startActivity(intent);
                                return;
                            }

                            i++;
                        }
                        txtPass.setText("");
                        Toast.makeText(getContext(), "Somthing Wrong ..!!\nCheck your Email or Password and try again", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<postSignUp>> call, Throwable t) {

                    }
                });

            }
        });


        return view;
    }
}