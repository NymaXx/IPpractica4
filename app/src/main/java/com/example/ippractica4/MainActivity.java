package com.example.ippractica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {


    private TextView num1;
    private TextView num2;
    private TextView num3;
    private TextView num4;
    private Button pingButton;
    private Button hostButton;
    private TextView myIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 127.0.0.1 es SIEMPRE la direccion IP de uno mismo, no importa donde este, eso es para
        //poder hacerse ping a si mismo de manera facil y conveniente

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        pingButton = findViewById(R.id.pingButton);
        hostButton = findViewById(R.id.hostButton);
        myIP = findViewById(R.id.myIP);

        pingButton.setOnClickListener(this);
        hostButton.setOnClickListener(this);




        new Thread(
                () -> {
                    try {
                        String ip = "127.0.0.1"; //autoreferencia
                        InetAddress inet = InetAddress.getByName(ip);
                        //con esto estoy haciendo 5 pings
                        for(int i=0 ; i<5 ; i++){
                            boolean conectado = inet.isReachable(1000);
                            Log.e(">>>", "conectado:" +conectado);
                        }


                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pingButton:

                ip = num1.getText().toString() + "." + num2.getText().toString() + "." + num3.getText().toString() + "." + num4.getText().toString();

                Intent intent = new Intent(this, Recibido.class);
                intent.putExtra("Ip", ip);
                startActivity(intent);

                break;

            case R.id.botonbuscarH:

                Intent intent1 = new Intent(this, IpRecibido.class);
                startActivity(intent1);

                break;


        }

    }

    public static String formatIpAddress(int ip) {
        return String.format(Locale.US, "%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
    }
}