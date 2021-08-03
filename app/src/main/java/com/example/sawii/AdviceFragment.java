package com.example.sawii;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdviceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView imgFav,imgFav2,imgFav3,imgFav4,imgFav5,imgFav6,imgFav7,imgFav8;
    private int src = R.drawable.heart;
    LinearLayout layoutAdvice1, layoutAdvice2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdviceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AdviceFragment newInstance(String param1, String param2) {
        AdviceFragment fragment = new AdviceFragment();
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


    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    RecyclerView RecyclerView;
    private ApiInterface apiInterface;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advice, container, false);

        RecyclerView = view.findViewById(R.id.Recycler_view);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);


        Call<List<PostAdvice>> call = apiInterface.getPost();

        call.enqueue(new Callback<List<PostAdvice>>() {
            @Override
            public void onResponse(Call<List<PostAdvice>> call, Response<List<PostAdvice>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<PostAdvice> postAdvices = response.body();

                int i = 0;
                for (PostAdvice postAdvice : postAdvices) {

                    list1.add("Advice " + i);
                    list2.add(postAdvice.getAdv_content());


                    i++;
                }
                String[] s1 = new String[list1.size()];
                String[] s2 = new String[list2.size()];


                list1.toArray(s1);
                list2.toArray(s2);

                AdviceRecyclerViewAdaptor adviceRecyclerViewAdaptor =  new AdviceRecyclerViewAdaptor(getContext(),s1,s2);

                RecyclerView.setAdapter(adviceRecyclerViewAdaptor);
                RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<List<PostAdvice>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}