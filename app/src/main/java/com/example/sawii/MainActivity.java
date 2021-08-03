package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgLogoText,imgLogoBoy,imgLogoLight;

    Animation LogoAnim,LogoBoyAnim,LogoLightAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,infoScreen.class);
                startActivity(intent);
                finish();
            }
        },6000);

        LogoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_anim);
        LogoBoyAnim = AnimationUtils.loadAnimation(this,R.anim.logo_boy_anim);
        LogoLightAnim = AnimationUtils.loadAnimation(this,R.anim.logo_light_anim);

        imgLogoText = findViewById(R.id.logo_text);
        imgLogoText.setAnimation(LogoAnim);

        imgLogoBoy = findViewById(R.id.logo_boy);
        imgLogoBoy.setAnimation(LogoBoyAnim);

        imgLogoLight = findViewById(R.id.logo_light);
        imgLogoLight.setAnimation(LogoLightAnim);

    }
}