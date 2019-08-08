package com.example.connect3;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0; //0: giallo, 1: verde, 2: vuoto

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}, {0,3,6}, {1,4,7}, {3,5,8}};

    String playedString;

    boolean gameActive = true;

    int mosse = 0;





    public void onClick(View view){
        ImageView counter =(ImageView) view;

        Log.i("info","Premuto "+ counter.getTag());


        int tappedCounter = Integer.valueOf(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            mosse++;

            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.giallo);
                activePlayer++;
                playedString = "Giallo";
            } else {
                counter.setImageResource(R.drawable.verde);
                activePlayer--;
                playedString = "Verde";
            }
            counter.animate().translationY(0).rotation(720).setDuration(500);
            //controllo
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    // qualcuno ha vinto
                    Toast.makeText(this, playedString + " has won!", Toast.LENGTH_SHORT).show();
                    gameActive=false;
                    TextView winnerTextView = (TextView) findViewById(R.id.textViewWinner);
                    Button buttonReset = (Button) findViewById(R.id.buttonReset);

                    winnerTextView.setText(playedString + " has won!");
                    if (playedString=="Giallo"){
                        winnerTextView.setTextColor(Color.parseColor("#FFFF00"));
                    } else {winnerTextView.setTextColor(Color.parseColor("#008000"));}
                    buttonReset.setVisibility(View.VISIBLE);
                }
            }
        }
        else if (mosse>8){
            Button buttonReset = (Button) findViewById(R.id.buttonReset);
            buttonReset.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view) {
        TextView winnerTextView = (TextView) findViewById(R.id.textViewWinner);
        Button buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonReset.setVisibility(View.INVISIBLE);
        winnerTextView.setText("Connect3");
        winnerTextView.setTextColor(Color.parseColor("#3F51B5"));


        /*
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageResource(R.drawable.giallo);
        }

        */

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        int count=gridLayout.getChildCount();
        for(int i=0; i<count; i++){
            ImageView counter =(ImageView)gridLayout.getChildAt(i);
            counter.setImageResource(0);
        }

        for(int i=0; i<gameState.length; i++){
            gameState[i]=2;
        }

        activePlayer = 0;
        gameActive = true;
        mosse=0;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
