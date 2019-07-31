package edu.csc4360.project2.wineinventory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

public class SearchActivity extends AppCompatActivity {
    WineDatabaseHandler db;

    private boolean isFragmentDisplayed = false;

    private Button searchbtn;
    private Button ratebtn;

    private EditText brandText;
    private ConstraintLayout constraintLayout;

    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setTitle("Search Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new WineDatabaseHandler(this);

        getSupportActionBar().setTitle("Search Item");

        constraintLayout = findViewById(R.id.view_activity_constraint);

        registerForContextMenu(constraintLayout);

        brandText =(EditText) findViewById(R.id.typeText);

        ratebtn = (Button) findViewById(R.id.ratebtn);

        searchbtn = (Button) findViewById(R.id.searchbtn);
        search();

        ratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor result = db.search(brandText.getText().toString());
                if (result.getCount() == 0) {
                    //message
                    showMessage("Error","no data");
                    return;
                }

                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                ratebtn.setText("Close");
            }
        }

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_all_extended:
                viewAll();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void viewAll(){
        Cursor result = db.getAllData();
        if (result.getCount() == 0) {
            //message
            showMessage("Error","no data");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext()){
            buffer.append("wineId :"+ result.getString(0)+"\n");
            buffer.append("model :"+ result.getString(1)+"\n");
            buffer.append("brand :"+ result.getString(2)+"\n");
            buffer.append("type :"+ result.getString(3)+"\n");
            buffer.append("year :"+ result.getString(4)+"\n");
            buffer.append("cost :"+ result.getString(5)+"\n\n");
        }
        showMessage("data",buffer.toString());
    }


    public void displayFragment() {
        HeaderFragment headerFragment = HeaderFragment.newInstance();
        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container, headerFragment).addToBackStack(null).commit();
        // Update the Button text.
        ratebtn.setText("Close");
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        HeaderFragment headerFragment = (HeaderFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (headerFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(headerFragment).commit();
        }
        // Update the Button text.
        ratebtn.setText("Rate");
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;
    }

    public void search(){
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = db.search(brandText.getText().toString());
                if (result.getCount() == 0) {
                    //message
                    showMessage("Error","no data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("wineId :"+ result.getString(0)+"\n");
                    buffer.append("model :"+ result.getString(1)+"\n");
                    buffer.append("brand :"+ result.getString(2)+"\n");
                    buffer.append("type :"+ result.getString(3)+"\n");
                    buffer.append("year :"+ result.getString(4)+"\n");
                    buffer.append("cost :"+ result.getString(5)+"\n\n");
                }
                showMessage("Item Information",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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
                actionAdd();
                break;

            case R.id.view:
                actionView();
                break;

            case R.id.edit:
                actionEdit();
                break;

            case R.id.search:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionAdd()
    {
        Intent i = new Intent(SearchActivity.this, AddActivity.class);
        startActivity(i);
    }

    private void actionView()
    {
        Intent i = new Intent(SearchActivity.this, ViewActivity.class);
        startActivity(i);
    }

    private void actionEdit()
    {
        Intent i = new Intent(SearchActivity.this, EditActivity.class);
        startActivity(i);
    }
}
