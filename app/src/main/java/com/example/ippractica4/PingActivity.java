package com.example.ippractica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingActivity extends AppCompatActivity {

    private TextView textPing;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);


        textPing = findViewById(R.id.textPing);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(
                (v)->{
                    finish();
                }
        );


        String result;
        String Ip = getIntent().getExtras().getString("ipToPing");

        new Thread(
                () ->{
                    try {
                        int p = 0;
                        while (p<5){
                            InetAddress inetAddress = InetAddress.getByName(Ip);
                            boolean isReachable = inetAddress.isReachable(1000);
                            Thread.sleep(2000);

                            if (isReachable) {

                                runOnUiThread(
                                        () -> {
                                            textPing.setText(textPing.getText().toString() + "Recibido\n");
                                        }
                                );
                            } else {

                                runOnUiThread(
                                        () -> {
                                            textPing.setText(textPing.getText().toString() + "Perdido \n");
                                        }
                                );
                            }
                            p++;
                        }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

    }

}