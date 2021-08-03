package com.example.sawii;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.mychatapp.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageActivity extends AppCompatActivity {


    String friendid, message, myid, friendname, friendmajor;
    CircleImageView imageViewOnToolbar;
    TextView usernameonToolbar;
    Toolbar toolbar;
    FirebaseUser firebaseUser;

    EditText et_message;
    Button send;

    DatabaseReference reference;

    List<postMessages> chatsList;
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    ValueEventListener seenlistener;
    checkingIdentification ch = new checkingIdentification();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        toolbar = findViewById(R.id.toolbar_message);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageViewOnToolbar = findViewById(R.id.profile_image_toolbar_message);
        usernameonToolbar = findViewById(R.id.username_ontoolbar_message);

        recyclerView = findViewById(R.id.recyclerview_messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        send = findViewById(R.id.send_messsage_btn);
        et_message = findViewById(R.id.edit_message_text);

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myid = ch.id + ""; // my id or the one who is loggedin

        friendid = getIntent().getStringExtra("friendid"); // retreive the friendid when we click on the item
        friendname = getIntent().getStringExtra("friendname"); // retreive the friendid when we click on the item
        friendmajor = getIntent().getStringExtra("friendmajor"); // retreive the friendid when we click on the item


        usernameonToolbar.setText(friendname); // set the text of the user on textivew in toolbar

        imageViewOnToolbar.setImageResource(R.drawable.profile);




        readMessages(myid, friendid);




//        seenMessage(friendid);






        et_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.toString().length() > 0) {

                    send.setEnabled(true);

                } else {

                    send.setEnabled(false);


                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = et_message.getText().toString();

                if (!text.startsWith(" ")) {
                    et_message.getText().insert(0, " ");

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                message = et_message.getText().toString();

                sendMessage(myid, friendid, message);

                et_message.setText("");


            }
        });

        et_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readMessages(myid, friendid);
            }
        });
    }
//
//
//
//    private void seenMessage(final String friendid) {
//
//        reference = FirebaseDatabase.getInstance().getReference("Chats");
//
//
//        seenlistener = reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot ds: snapshot.getChildren()) {
//
//                    Chats chats = ds.getValue(Chats.class);
//
//                    if (chats.getReciever().equals(myid) && chats.getSender().equals(friendid)) {
//
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("isseen", true);
//                        ds.getRef().updateChildren(hashMap);
//
//                    }
//
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//
//
//
//    }
//
    private void readMessages(final String myid, final String friendid) {

        chatsList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List<postMessages>> call = apiInterface.getMessages();

        call.enqueue(new Callback<List<postMessages>>() {
            @Override
            public void onResponse(Call<List<postMessages>> call, Response<List<postMessages>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                chatsList.clear();

                List<postMessages> getusername = response.body();

                for (postMessages postMessages : getusername) {

                    postMessages chats = postMessages;

                    if (chats.getFrom_user_id() == Integer.parseInt(myid) && chats.getTo_user_id() == Integer.parseInt(friendid) ||
                            chats.getFrom_user_id() == Integer.parseInt(friendid) && chats.getTo_user_id() == Integer.parseInt(myid)) {

                        chatsList.add(chats);
                    }

                    messageAdapter = new MessageAdapter(MessageActivity.this, chatsList);
                    recyclerView.setAdapter(messageAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<postMessages>> call, Throwable t) {

            }
        });










    }




    private void sendMessage(final String myid, final String friendid, final String message) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sawy.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<postMessages> call = apiInterface.createMessage(Integer.parseInt(friendid),Integer.parseInt(myid),message,dtf.format(now),1);

        call.enqueue(new Callback<postMessages>() {
            @Override
            public void onResponse(Call<postMessages> call, Response<postMessages> response) {

            }

            @Override
            public void onFailure(Call<postMessages> call, Throwable t) {

            }
        });

        readMessages(myid, friendid);

    }


}