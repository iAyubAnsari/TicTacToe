package com.ayubansari.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout playAgainLayout;

    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 means unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainLayout=(LinearLayout)findViewById(R.id.playAgainLayout);

    }

    public void dropIn(View view) {

        ImageView iconRedYellow = (ImageView) view;

        int tappedCounter = Integer.parseInt(iconRedYellow.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            iconRedYellow.setTranslationY(-1000f);

            if (activePlayer == 0) {

                iconRedYellow.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                iconRedYellow.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            iconRedYellow.animate().translationYBy(1000f).rotation(360).setDuration(100);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won!

                    gameIsActive = false;

                    String winner = "Red";
                    playAgainLayout.setBackgroundColor(Color.parseColor("#DD2C00"));

                    if (gameState[winningPosition[0]] == 0) {

                        winner = "Yellow";
                        playAgainLayout.setBackgroundColor(Color.parseColor("#FFEB3B"));

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    // playAgainLayout= (LinearLayout)findViewById(R.id.playAgainLayout);

                    playAgainLayout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");
                        playAgainLayout.setBackgroundColor(Color.parseColor("#00c853"));

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }
        }


    }

    public void playAgain(View view) {

        gameIsActive = true;

        playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

        playAgainLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

}