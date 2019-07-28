package edu.csc4360.project2.wineinventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import edu.csc4360.project2.wineinventory.View.GifImageView;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private Button view;
    private Button edit;
    private Button add;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //when search button is clicked, redirect to new activity
        search = (Button) findViewById(R.id.searchbtn);
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        view = (Button) findViewById(R.id.viewbtn);
        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ViewActivity.class));

            }
        });

        edit = (Button) findViewById(R.id.editdelbtn);
        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, EditActivity.class));

            }
        });

        add = (Button) findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AddActivity.class));

            }
        });

        img = (ImageView) findViewById(R.id.imageView);


    }
}