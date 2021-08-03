package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraMetadata;
import android.media.AudioRecord;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class call extends AppCompatActivity {
        FloatingActionButton btnBack, btnMic, btnVideo, btnCall;
        ImageView imgHim, imgSelf;
        boolean mic_on_off = false, video_on_off = false;

        checkingIdentification ch = new checkingIdentification();


    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);



        webView = findViewById(R.id.webView);

//        String url = "https://sawy.000webhostapp.com/HasonaCam/";
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {

            CookieManager cookieManager = CookieManager.getInstance();

            cookieManager.setAcceptThirdPartyCookies(webView,true);
        }

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (ContextCompat.checkSelfPermission(call.this,
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                ) {

                    ActivityCompat.requestPermissions(call.this,
                            new String[]{Manifest.permission.RECORD_AUDIO}, AudioRecord.RECORDSTATE_RECORDING);
                }


                if (ContextCompat.checkSelfPermission(call.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ) {

                    ActivityCompat.requestPermissions(call.this,
                            new String[]{Manifest.permission.CAMERA}, CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_SYSTEM_CAMERA);
                }


//                if (ContextCompat.checkSelfPermission(call.this,
//                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
//                ) {
//
//                    ActivityCompat.requestPermissions(call.this,
//                            new String[]{Manifest.permission.RECORD_AUDIO}, AudioRecord.RECORDSTATE_RECORDING);
//                }

            }
        });


        if (savedInstanceState == null) {
            webView.loadUrl("https://sawy.000webhostapp.com/HasonaCam/");
        }

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.getResources());
                }
            }

        });



        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

//--------------------------------------------------------------------------------------------------

//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//
//
//        webView.loadUrl("https://sawy.000webhostapp.com/HasonaCam/");









        btnBack = findViewById(R.id.btn_back);
        btnMic = findViewById(R.id.btn_mic);
        btnVideo = findViewById(R.id.btn_video);
        btnCall = findViewById(R.id.btn_call);
//        imgHim = findViewById(R.id.img_him);
//        imgSelf = findViewById(R.id.img_self);
//
//
//        if (ch.name.equals("doctor")){
//            imgHim.setImageResource(R.drawable.photo_call2);
//            imgSelf.setImageResource(R.drawable.photo_doctor);
//        } else {
//            imgHim.setImageResource(R.drawable.photo_doctor);
//            imgSelf.setImageResource(R.drawable.photo_call2);
//        }
//
//
//
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ch.name.equals("doctor")){
                    Intent intent = new Intent(getBaseContext(), Prescription.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getBaseContext(), rate.class);
                    startActivity(intent);
                }

                finish();
            }
        });

        btnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mic_on_off == false) {
                    btnMic.setImageResource(R.drawable.mic_non);
                    mic_on_off = true;
                } else {
                    btnMic.setImageResource(R.drawable.mic);
                    mic_on_off = false;
                }

            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(video_on_off == false) {
                    btnVideo.setImageResource(R.drawable.video_call_non);
                    video_on_off = true;
                } else {
                    btnVideo.setImageResource(R.drawable.video_call);
                    video_on_off = false;
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}