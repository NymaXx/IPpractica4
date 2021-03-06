package com.example.ippractica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostActivity extends AppCompatActivity {



    private TextView hostText;
    private Button hostBack;
    private String receiveIp;
    private int ip;

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


        new Thread(

                () -> {
                    while (ip < 255) {
                        String finalData = Integer.toString(ip);
                        try {
                            Thread.sleep(1000);
                            InetAddress inetAddress = InetAddress.getByName(receiveIp + "" + finalData);
                            Boolean isReachable = inetAddress.isReachable(1000);
                            if (isReachable) {
                                runOnUiThread(
                                        () -> {
                                            hostText.setText(hostText.getText().toString() + ""
                                                    + receiveIp + "" + finalData + "\n");
                                        }
                                );
                            }
                            ip++;
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }

}