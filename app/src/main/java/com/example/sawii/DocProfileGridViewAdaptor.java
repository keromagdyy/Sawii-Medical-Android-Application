package com.example.sawii;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocProfileGridViewAdaptor extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] docName;
    private String[] bio;
    private String[] price;

    int Id = 0;
    String Name = "";
    String Bio = "";
    int Price = 0;
    String City = "";
    String Spec = "";
    int YOE = 0;


    public DocProfileGridViewAdaptor(Context context, String[] docName, String[] bio, String[] price){
        this.context = context;
        this.docName = docName;
        this.bio = bio;
        this.price = price;
    }

    @Override
    public int getCount() {
        return docName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_booking, null);
        }

        TextView txtUsername = convertView.findViewById(R.id.txtUsername);
        TextView txtBio = convertView.findViewById(R.id.txtBio);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        ApiInterface apiInterface;


        txtUsername.setText(docName[position]);
        txtBio.setText(bio[position]);
        txtPrice.setText(price[position]);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Call<List<postSignUp>> callGetDocId = apiInterface.getloginUser();

        callGetDocId.enqueue(new Callback<List<postSignUp>>() {
            @Override
            public void onResponse(Call<List<postSignUp>> call, Response<List<postSignUp>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<postSignUp> getid = response.body();

                int i = 0;
                for (postSignUp postSignUp : getid) {

                    if (txtUsername.getText().toString().equals(postSignUp.getUsername())){
                        Id = postSignUp.getUser_id();
                        return;
                    }
                    i++;
                }
            }

            @Override
            public void onFailure(Call<List<postSignUp>> call, Throwable t) {

            }
        });


        Call<List<postDocProfile>> callGetDoc = apiInterface.getDocProfile();

        callGetDoc.enqueue(new Callback<List<postDocProfile>>() {
            @Override
            public void onResponse(Call<List<postDocProfile>> call, Response<List<postDocProfile>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<postDocProfile> getid = response.body();

                int i = 0;
                for (postDocProfile postDoc : getid) {

                    if (postDoc.getDoc_id() == Id){
                        Name = txtUsername.getText().toString();
                        Bio = postDoc.getBio();
                        Price = postDoc.getPrice();
                        City = postDoc.getCountry();
                        Spec = postDoc.getSpecialties();
                        YOE = postDoc.getYear_of_ex();

                        return;
                    }

                    i++;
                }
            }

            @Override
            public void onFailure(Call<List<postDocProfile>> call, Throwable t) {

            }
        });

        return convertView;
    }
}
