package com.example.sawii;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsRecyclerViewAdaptor extends RecyclerView.Adapter<PostsRecyclerViewAdaptor.MyViewHolder> {
    private String   UserName[];
    private String post_date_time[];
    private String post_content[];
    Context context;

    public PostsRecyclerViewAdaptor(Context context, String[] UserName, String[] post_date_time, String[] post_content) {
        this.context = context;
        this.UserName = UserName;
        this.post_date_time = post_date_time;
        this.post_content = post_content;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_posts, parent, false);
//        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.txtUserName.setText(UserName[position]);
        holder.txtDate.setText(post_date_time[position]);
        holder.txtPostContant.setText(post_content[position]);
    }

    @Override
    public int getItemCount() {
        return post_content.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserName, txtDate, txtPostContant;
        Button btnReply;
        ImageView profilePic;
        checkingIdentification ch = new checkingIdentification();
        ApiInterface apiInterface;
        int id = 0;
        String name = "";

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPostContant = itemView.findViewById(R.id.txtPostContant);
            btnReply = itemView.findViewById(R.id.btnReply);
            profilePic = itemView.findViewById(R.id.profilePic);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://sawy.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);

            Call<List<postSignUp>> callGetPat = apiInterface.getloginUser();

            callGetPat.enqueue(new Callback<List<postSignUp>>() {
                @Override
                public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "code : " + response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    List<postSignUp> getlog2 = response.body();

                    int i = 0;
                    for (postSignUp postSignUp : getlog2) {

                        if (txtUserName.getText().toString().equals(postSignUp.getUsername())){
                            id = postSignUp.getUser_id();
                            name = txtUserName.getText().toString();

                            return;
                        }

                        i++;
                    }
                }

                @Override
                public void onFailure(Call<List<postSignUp>> call, Throwable t) {

                }
            });

            if (ch.name.equals("doctor")){
                btnReply.setVisibility(View.VISIBLE);
            } else {
                btnReply.setVisibility(View.GONE);
            }

            btnReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, comment.class);
                    context.startActivity(intent);
                }
            });

            profilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, profilePatient.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    context.startActivity(intent);
                }
            });

            txtUserName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    Intent intent = new Intent(context, profilePatient.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    context.startActivity(intent);
                }
            });

        }
    }
}
