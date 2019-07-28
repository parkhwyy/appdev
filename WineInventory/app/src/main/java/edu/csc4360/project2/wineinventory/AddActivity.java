package edu.csc4360.project2.wineinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

public class AddActivity extends AppCompatActivity {
    WineDatabaseHandler db;

    ImageButton addbtn;
    EditText wineText, brandText, yearText, costText, quantityText, typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = new WineDatabaseHandler(this);

        getSupportActionBar().setTitle("Add Item");

        wineText = (EditText) findViewById(R.id.nameText);
        brandText = (EditText) findViewById(R.id.brandText);
        yearText = (EditText) findViewById(R.id.yearText);
        costText = (EditText) findViewById(R.id.priceText);
        quantityText = (EditText) findViewById(R.id.quantityText);
        typeText = (EditText) findViewById(R.id.typeText);

        addbtn = (ImageButton) findViewById(R.id.add_item_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    public void openDialog() {
        AddDialog exampleDialog = new AddDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.add:
                break;

            case R.id.view:
                actionView();
                break;

            case R.id.edit:
                actionEdit();
                break;

            case R.id.search:
                actionSearch();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionView()
    {
        Intent i = new Intent(AddActivity.this, ViewActivity.class);
        startActivity(i);
    }

    private void actionEdit()
    {
        Intent i = new Intent(AddActivity.this, EditActivity.class);
        startActivity(i);
    }

    private void actionSearch()
    {
        Intent i = new Intent(AddActivity.this, SearchActivity.class);
        startActivity(i);
    }

}
