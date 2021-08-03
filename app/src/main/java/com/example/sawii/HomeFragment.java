package com.example.sawii;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int src = R.drawable.heart;
    private FloatingActionButton btnAddPost;
    private RecyclerView RecyclerView;
    private ApiInterface apiInterface;
    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> list3 = new ArrayList<String>();
    checkingIdentification ch = new checkingIdentification();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView = view.findViewById(R.id.Recycler_view);
        btnAddPost = view.findViewById(R.id.btnAddPost);


        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPost.class);
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
                    Toast.makeText(getContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<getPosts> posts = response.body();

                int i = 0;
                for (getPosts post : posts) {

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

                            List<postSignUp> getlog2 = response.body();

                            int i = 0;
                            for (postSignUp postSignUp : getlog2) {

                                if (post.getPat_id() == postSignUp.getUser_id()){

                                    list1.add(postSignUp.getUsername());
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

                            PostsRecyclerViewAdaptor postAdaptor =  new PostsRecyclerViewAdaptor(getContext(),s1,s2,s3);

                            RecyclerView.setAdapter(postAdaptor);
                            RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        @Override
                        public void onFailure(Call<List<postSignUp>> call, Throwable t) {

                        }
                    });

                    i++;
                }

            }

            @Override
            public void onFailure(Call<List<getPosts>> call, Throwable t) {

            }
        });


        return view;
    }
}