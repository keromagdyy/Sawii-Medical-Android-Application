package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class ForgetPassword extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    CheckBox checkRobot;
    Button btnRecover;
    GoogleApiClient googleApiClient;
    String SiteKey = "6Le2wScaAAAAAF-rww6wUBMTE64cguZfljf46DHc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forget Password ?");

        checkRobot = findViewById(R.id.check_robot);
        btnRecover = findViewById(R.id.btn_Revover);

        btnRecover.setEnabled(false);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(ForgetPassword.this)
                .build();
        googleApiClient.connect();

        checkRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRobot.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if((status != null) && status.isSuccess()) {
                                        Toast.makeText(ForgetPassword.this, "Succsesfully Varified...", Toast.LENGTH_LONG).show();
                                        checkRobot.setTextColor(Color.BLUE);
                                        btnRecover.setEnabled(true);
                                    }
                                    else {
                                        checkRobot.setTextColor(Color.BLACK);
                                        btnRecover.setEnabled(false);
                                    }
                                }
                            });
                }
            }
        });


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}