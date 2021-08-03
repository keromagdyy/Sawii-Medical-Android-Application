package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    Animation LogoAnim;
    ImageView imgLogo,imgTringle1,imgTringle2;
    Button btnSignUp,btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgLogo = findViewById(R.id.img_login_logo);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        imgTringle1 = findViewById(R.id.tringle1);
        imgTringle2 = findViewById(R.id.tringle2);


        LogoAnim = AnimationUtils.loadAnimation(this,R.anim.login_logo_anim);
        imgLogo.setAnimation(LogoAnim);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        LoginFrag loginFrag = new LoginFrag();
        Signup signupFrag = new Signup();
        ft.replace(R.id.fragment,loginFrag);
        ft.commit();

        btnSignIn.setBackgroundResource(R.drawable.bottom_btn_signinup);
        btnSignUp.setBackgroundResource(R.drawable.bottom_layout_login);
        imgTringle1.setVisibility(View.INVISIBLE);
        imgTringle2.setVisibility(View.VISIBLE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm2 = getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                ft2.replace(R.id.fragment,loginFrag);
                ft2.commit();

                imgLogo.setVisibility(View.VISIBLE);
                imgTringle1.setVisibility(View.INVISIBLE);
                imgTringle2.setVisibility(View.VISIBLE);

                btnSignIn.setBackgroundResource(R.drawable.bottom_btn_signinup);
                btnSignUp.setBackgroundResource(R.drawable.bottom_layout_login);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm2 = getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                ft2.replace(R.id.fragment,signupFrag);
                ft2.commit();

                imgLogo.setVisibility(View.GONE);
                imgTringle1.setVisibility(View.VISIBLE);
                imgTringle2.setVisibility(View.INVISIBLE);

                btnSignIn.setBackgroundResource(R.drawable.bottom_layout_login);
                btnSignUp.setBackgroundResource(R.drawable.bottom_btn_signinup);
            }
        });

    }
}