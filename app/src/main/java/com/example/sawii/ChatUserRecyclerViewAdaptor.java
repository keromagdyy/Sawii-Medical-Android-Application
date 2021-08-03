package com.example.sawii;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatUserRecyclerViewAdaptor extends RecyclerView.Adapter<ChatUserRecyclerViewAdaptor.ViewHolder> {
    private Context context;
    private String userList[];
    String s = "";
    String friendid = "";

    public ChatUserRecyclerViewAdaptor(Context context, String userList[]){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_chat_users,parent,false);

        return new ChatUserRecyclerViewAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.txtUserName.setText(userList[position]);
    }

    @Override
    public int getItemCount() {
        return userList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtUserName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtUserName = itemView.findViewById(R.id.txtUserName);

            itemView.setBackgroundResource(R.drawable.chatrecycler);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://sawy.000webhostapp.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                    Call<List<postSignUp>> callGetUserName = apiInterface.getloginUser();

                    callGetUserName.enqueue(new Callback<List<postSignUp>>() {
                        @Override
                        public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(context, "code : " + response.code(), Toast.LENGTH_LONG).show();
                                return;
                            }

                            List<postSignUp> getusername = response.body();

                            for (postSignUp postSignUp : getusername) {

                                if (txtUserName.getText().toString().equals(postSignUp.getUsername())){
                                    friendid = postSignUp.getUser_id()+"";
                                    Intent intent = new Intent(context, MessageActivity.class);
                                    intent.putExtra("friendid", friendid);
                                    intent.putExtra("friendname", postSignUp.getUsername());
                                    intent.putExtra("friendmajor", postSignUp.getMajor()+"");
                                    context.startActivity(intent);
                                }

                            }


                        }

                        @Override
                        public void onFailure(Call<List<postSignUp>> call, Throwable t) {

                        }
                    });




                }
            });
        }
    }
}
