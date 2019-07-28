package edu.csc4360.project2.wineinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

public class ViewActivity extends AppCompatActivity {

    private ArrayList<String> wineNames = new ArrayList<>();
    private WineDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().setTitle("View All Items");

        db = new WineDatabaseHandler(this);

        this.wineNames = getWineNames();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(wineNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<String> getWineNames() {
        WineDatabaseHandler db = new WineDatabaseHandler(this);
        ArrayList<String> wineNames = new ArrayList<>();

        Cursor cursor = db.getAllData();
        while(cursor.moveToNext()) {
            wineNames.add(cursor.getString(1));
        }

        return wineNames;
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

    private void actionAdd()
    {
        Intent i = new Intent(ViewActivity.this, AddActivity.class);
        startActivity(i);
    }

    private void actionEdit()
    {
        Intent i = new Intent(ViewActivity.this, EditActivity.class);
        startActivity(i);
    }

    private void actionSearch()
    {
        Intent i = new Intent(ViewActivity.this, SearchActivity.class);
        startActivity(i);
    }

}
