package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonadd, buttonsub, buttondiv,
            buttonmul, buttonac, buttonans;
    EditText ansText;

    float val1, val2;

    boolean add, sub, mul, div, ans = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsub = (Button) findViewById(R.id.buttonsub);
        buttonmul = (Button) findViewById(R.id.buttonmul);
        buttondiv = (Button) findViewById(R.id.buttondiv);
        buttonac = (Button) findViewById(R.id.buttonac);
        buttonans = (Button) findViewById(R.id.buttonans);
        ansText = (EditText) findViewById(R.id.ansText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans) {
                    ansText.setText(null);
                    ans = false;
                }
                ansText.setText(ansText.getText() + "0");
            }
        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ansText == null) {
                    ansText.setText("");
                } else {
                    val1 = Float.parseFloat(ansText.getText() + "");
                    add = true;
                    ansText.setText(null);
                }
            }
        });

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Float.parseFloat(ansText.getText() + "");
                sub = true;
                ansText.setText(null);
            }
        });

        buttonmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Float.parseFloat(ansText.getText() + "");
                mul = true;
                ansText.setText(null);
            }
        });

        buttondiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Float.parseFloat(ansText.getText() + "");
                div = true;
                ansText.setText(null);
            }
        });

        buttonans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val2 = Float.parseFloat(ansText.getText() + "");

                if (add == true) {
                    ansText.setText(val1 + val2 + "");
                    add = false;
                }

                if (sub == true) {
                    ansText.setText(val1 - val2 + "");
                    sub = false;
                }

                if (mul == true) {
                    ansText.setText(val1 * val2 + "");
                    mul = false;
                }

                if (div == true) {
                    ansText.setText(val1 / val2 + "");
                    div = false;
                }

                ans = true;
            }
        });

        buttonac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansText.setText("");
            }
        });

    }
}
