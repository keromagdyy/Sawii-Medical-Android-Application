package com.example.sawii;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class chatRegestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public chatRegestFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static chatRegestFragment newInstance(String param1, String param2) {
        chatRegestFragment fragment = new chatRegestFragment();
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


    private RecyclerView recyclerView;
    private List<String> userList;
    ApiInterface apiInterface;
    checkingIdentification ch = new checkingIdentification();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_regest, container, false);

        recyclerView = view.findViewById(R.id.Recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Call<List<postSignUp>> callGetUserName = apiInterface.getloginUser();

        callGetUserName.enqueue(new Callback<List<postSignUp>>() {
            @Override
            public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<postSignUp> getusername = response.body();

                int i = 0;
                for (postSignUp postSignUp : getusername) {

                    if (ch.id != postSignUp.getUser_id()){
                        userList.add(postSignUp.getUsername());
                    }

                    i++;
                }
                String[] s1  = new String[userList.size()];


                userList.toArray(s1);

                ChatUserRecyclerViewAdaptor adaptor = new ChatUserRecyclerViewAdaptor(getContext(),s1);
                recyclerView.setAdapter(adaptor);


            }

            @Override
            public void onFailure(Call<List<postSignUp>> call, Throwable t) {

            }
        });


        return view;
    }
}