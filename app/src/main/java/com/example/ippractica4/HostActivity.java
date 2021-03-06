package com.example.ippractica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HostActivity extends AppCompatActivity {



    private TextView hostText;
    private Button hostBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        hostText = findViewById(R.id.hostText);
        hostBack = findViewById(R.id.hostBack);

        hostBack.setOnClickListener(
                (v) -> {
                    finish();
                }
        );

    }

    public void onClick (View v){

    }
}