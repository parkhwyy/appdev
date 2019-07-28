package com.example.simon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    View red;
    View green;
    View blue;
    View yellow;
    TextView score;

    final int MAX_LENGTH = 1000;
    int[] sequence = new int[MAX_LENGTH];

    final Handler handler = new Handler();
    Random rand = new Random();

    public int clicks = 0, elements = 0, k = 0,  highScore = 0, x, pace, layoutId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        layoutId = 1;

        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        yellow = findViewById(R.id.yellow);
        score = findViewById(R.id.scoreView);

        red.setOnTouchListener(onTouch);
        green.setOnTouchListener(onTouch);
        blue.setOnTouchListener(onTouch);
        yellow.setOnTouchListener(onTouch);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String a[] = {"Slow", "Normal", "Fast", "Faster"};
        builder.setTitle("Choose the pace.")

                .setItems(a, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int p) {
                        pace = p;
                        score.setText("Current score: " + elements + "           High score: " + highScore);

                        final Runnable r = new Runnable() {
                            public void run() {
                                playGame();
                            }
                        };
                        handler.postDelayed(r, 1500);
                    }
                });
        AlertDialog myDialog = builder.create();
        myDialog.show();

    }

    View.OnTouchListener onTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                switch (v.getId()) {
                    case R.id.red:
                        x = 1;
                        break;
                    case R.id.green:
                        x = 2;
                        break;
                    case R.id.blue:
                        x = 3;
                        break;
                    case R.id.yellow:
                        x = 4;
                        break;
                }
                if (sequence[clicks] != x) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameActivity.this);
                    alertDialogBuilder.setMessage("Game Over \n\nDo you want to play again?");
                    alertDialogBuilder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    clear();
                                    score.setText("Current score: " + elements + "           High score: " + highScore);
                                    playGame();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int p) {
                            backtoStartActivity();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    return true;
                }

                changeColor(v);
                clicks++;

                if (elements == clicks) {

                    clicks = 0;
                    if (elements > highScore) {
                        highScore = elements;
                    }
                    score.setText("Current score: " + elements + "           High score: " + highScore);


                    changeLayout();

                    final Runnable r = new Runnable() {
                        public void run() {
                            playGame();
                        }
                    };
                    handler.postDelayed(r, 1500 - 300 * pace);
                }
            }
            return true;
        }
    };

    private void changeColor(final View v) {
        v.getBackground().setAlpha(51);
        final Runnable r = new Runnable() {
            public void run() {
                v.getBackground().setAlpha(255);
            }
        };
        handler.postDelayed(r, 200);
    }

    private void changeLayout() {
        final Runnable r = new Runnable() {
            public void run() {
                switch (layoutId) {
                    case 1:
                        setContentView(R.layout.activity_game2);
                        layoutId = 2;

                        red = findViewById(R.id.red);
                        green = findViewById(R.id.green);
                        blue = findViewById(R.id.blue);
                        yellow = findViewById(R.id.yellow);
                        score = findViewById(R.id.scoreView);

                        red.setOnTouchListener(onTouch);
                        green.setOnTouchListener(onTouch);
                        blue.setOnTouchListener(onTouch);
                        yellow.setOnTouchListener(onTouch);
                        score.setText("Current score: " + elements + "           High score: " + highScore);
                        break;
                    case 2:
                        setContentView(R.layout.activity_game3);
                        layoutId = 3;

                        red = findViewById(R.id.red);
                        green = findViewById(R.id.green);
                        blue = findViewById(R.id.blue);
                        yellow = findViewById(R.id.yellow);
                        score = findViewById(R.id.scoreView);

                        red.setOnTouchListener(onTouch);
                        green.setOnTouchListener(onTouch);
                        blue.setOnTouchListener(onTouch);
                        yellow.setOnTouchListener(onTouch);
                        score.setText("Current score: " + elements + "           High score: " + highScore);
                        break;
                    case 3:
                        setContentView(R.layout.activity_game4);
                        layoutId = 4;

                        red = findViewById(R.id.red);
                        green = findViewById(R.id.green);
                        blue = findViewById(R.id.blue);
                        yellow = findViewById(R.id.yellow);
                        score = findViewById(R.id.scoreView);

                        red.setOnTouchListener(onTouch);
                        green.setOnTouchListener(onTouch);
                        blue.setOnTouchListener(onTouch);
                        yellow.setOnTouchListener(onTouch);
                        score.setText("Current score: " + elements + "           High score: " + highScore);
                        break;
                    case 4:
                        setContentView(R.layout.activity_game);
                        layoutId = 1;

                        red = findViewById(R.id.red);
                        green = findViewById(R.id.green);
                        blue = findViewById(R.id.blue);
                        yellow = findViewById(R.id.yellow);
                        score = findViewById(R.id.scoreView);

                        red.setOnTouchListener(onTouch);
                        green.setOnTouchListener(onTouch);
                        blue.setOnTouchListener(onTouch);
                        yellow.setOnTouchListener(onTouch);
                        score.setText("Current score: " + elements + "           High score: " + highScore);
                    }
            }
        };
        handler.postDelayed(r, 400);
    }

    public void playGame() {
        appendValueToArray();
        elements++;
        for (k = 0; k < elements; k++) {
            click(k);
        }
    }

    public void click(final int click_index) {
        final Runnable r = new Runnable() {
            public void run() {
                if (sequence[click_index] == 1) {
                    changeColor(red);
                } else if (sequence[click_index] == 2) {
                    changeColor(green);
                } else if (sequence[click_index] == 3) {
                    changeColor(blue);
                } else {
                    changeColor(yellow);
                }
            }
        };

        handler.postDelayed(r, (1500 - 300 * pace) * click_index);
    }


    private int generateRandomNumber() {
        return rand.nextInt(4) + 1;
    }

    private void appendValueToArray() {
        for (int i = 0; i < MAX_LENGTH; i++) {
            if (sequence[i] == 0) {
                sequence[i] = generateRandomNumber();
                break;
            }
        }
    }

    private void clear() {
        for (int i = 0; i < MAX_LENGTH; i++) {
            sequence[i] = 0;
        }
        clicks = 0;
        elements = 0;
        setContentView(R.layout.activity_game);
        layoutId = 1;

        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        yellow = findViewById(R.id.yellow);
        score = findViewById(R.id.scoreView);

        red.setOnTouchListener(onTouch);
        green.setOnTouchListener(onTouch);
        blue.setOnTouchListener(onTouch);
        yellow.setOnTouchListener(onTouch);
    }

    public void backtoStartActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}