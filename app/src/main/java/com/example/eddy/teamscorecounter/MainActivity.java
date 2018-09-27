package com.example.eddy.teamscorecounter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView teamUSA;
    private TextView teamDR;
    private Button buttonUSA;
    private Button buttonDR;
    String winneris;
    int DR = 0;
    int USA = 0;
    int diff = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamUSA = findViewById(R.id.score_TeamUSAView);
        teamDR = findViewById(R.id.score_TeamDRView);
        buttonUSA = findViewById(R.id.buttonUSA);
        buttonDR = findViewById(R.id.buttonDR);
        buttonUSA.setOnClickListener(this);
        buttonDR.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if(buttonUSA.equals(view)){
            USA++;
            teamUSA.setText(""+USA);
            if(USA == 5){
                diff = USA - DR;
                winneris = "Team USA";
                teamWon();
            }
        }
        if(buttonDR.equals(view)){
            DR++;
            teamDR.setText(""+DR);
            if(DR == 5){
                diff = DR - USA;
                winneris = "Team DR";
                teamWon();
            }
        }

    }
    public void teamWon(){

        DR = 0;
        USA = 0;
        teamDR.setText(""+DR);
        teamUSA.setText(""+USA);
        Intent intent = new Intent( this, WinnerActivity.class);
        Bundle extras = new Bundle();
        extras.putString("winner",winneris);
        extras.putString("diffpoints",Integer.toString(diff));
        intent.putExtras(extras);
        startActivity(intent);


    }
}
