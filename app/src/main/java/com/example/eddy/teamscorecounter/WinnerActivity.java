package com.example.eddy.teamscorecounter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

public class WinnerActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    private TextView Winview;
    private EditText phoneNumber;
    private Button Location;
    private Button Messaging;
    private Button Calling;
    private String goodNews;

    public String numberPH;
    public static final int REQUEST_CALL_PHONE = 1;

    String winner;
    String diffpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);



        Winview = findViewById(R.id.winnerView);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        winner = extras.getString("winner");
        diffpoints = extras.getString("diffpoints");
        Winview.setText(winner + " won by " + diffpoints + " points.");

        //MESSAGE TO SEND.
        goodNews = "I told you "+winner+ " was going to win by "+diffpoints+" points.";

        Location = findViewById(R.id.Locatepl);
        Messaging = findViewById(R.id.Sharemsg);
        Calling = findViewById(R.id.Call);
        phoneNumber = findViewById(R.id.getPhoneNumber);

        phoneNumber.addTextChangedListener(this);
        Location.setOnClickListener(this);
        Messaging.setOnClickListener(this);
        Calling.setOnClickListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        try {

            numberPH = (charSequence.toString());

        } catch (NumberFormatException e) {
            e.printStackTrace();
            numberPH = "";

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    @Override
    public void onClick(View view) {

        if (Location.equals(view)) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=volleyball+center"));
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }

        }
        if (Messaging.equals(view)) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:"+Uri.encode(numberPH)));
            intent.putExtra("sms_body", goodNews);
            startActivity(intent);

        }
        if (Calling.equals(view)) {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numberPH));

            if (intent.resolveActivity(getPackageManager()) != null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                }
                else {
                    startActivity(intent);
                }

            }
        }
    }
}
