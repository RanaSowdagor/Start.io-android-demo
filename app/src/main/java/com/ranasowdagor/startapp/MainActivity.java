package com.ranasowdagor.startapp;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RelativeLayout root = findViewById(R.id.main);
        Constant.startIo.loadBannerAd(this,root);
        //Constant.startIo.loadCoverAd(this,root);
        //Constant.startIo.loadNativeAd(this,root);
        findViewById(R.id.btn1).setOnClickListener(v -> {
            Constant.startIo.showAd(this, StartIo.AUTOMATIC, new StartIo.GlobalAdListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {
                    Toast.makeText(MainActivity.this, "complete", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String reason) {
                    Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();
                }
            });
        });
        findViewById(R.id.btn2).setOnClickListener(v -> {
            Constant.startIo.showAd(this,StartIo.FULL_PAGE);
        });
        findViewById(R.id.btn3).setOnClickListener(v -> {
            Constant.startIo.showAd(this,StartIo.OFFER_WALL);
        });
        findViewById(R.id.btn4).setOnClickListener(v -> {
            Constant.startIo.showAd(this,StartIo.REWARDED);
        });
    }
}