package com.example.laborator3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText display = findViewById(R.id.display);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        Button btn0 = findViewById(R.id.button0);
        Button btnStar = findViewById(R.id.buttonStar);
        Button btnSharp = findViewById(R.id.buttonSharp);
        ImageButton btnBackSpace = findViewById(R.id.buttonDelete);
        ImageButton btnCall = findViewById(R.id.buttonCall);
        ImageButton btnContacts = findViewById(R.id.contacts);

        final Button array[] = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnStar, btnSharp};
        for (int i = 0; i < array.length; i++){
            final int finalI = i;
            array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    display.setText(display.getText() + "" + array[finalI].getText());
                }
            });
        }

        btnBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable text = display.getText();
                if (text.length()>0) {
                    display.setText(text.subSequence(0, text.length() - 1));
                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + display.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = display.getText().toString();
                if (phoneNumber.length() > 0) {
                    Intent intent = new Intent("com.example.contactsmanager.intent.action.MainActivity");
                    intent.putExtra("com.example.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                    startActivityForResult(intent, 2);
                } else {
                    Toast.makeText(getApplication(), "Boule", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
