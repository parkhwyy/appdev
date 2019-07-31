package edu.csc4360.project2.wineinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

import edu.csc4360.project2.wineinventory.Model.WineDatabaseHandler;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<String> wineNames = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;
    private WineDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().setTitle("View All Items");
        db = new WineDatabaseHandler(this);

        mRecyclerView =findViewById(R.id.recycler_view);
        coordinatorLayout= findViewById(R.id.coordinatorLayout);

        populatelist();
        enableSwipeToDelete();

    }

    private void populatelist(){
        wineNames.add("Villa Wolf");
        wineNames.add("St. Chomes");

        Cursor cursor = db.getAllData();
        while(cursor.moveToNext()) {
            wineNames.add(cursor.getString(1)); //winename

        }
        adapter = new RecyclerViewAdapter(wineNames);
        mRecyclerView.setAdapter(adapter);
    }


    private void enableSwipeToDelete(){
        SwipeDeleteCallback swipeDeleteCallback = new SwipeDeleteCallback(this){
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i){

                final int position = viewHolder.getAdapterPosition();
                final String item = adapter.getData().get(position);


                adapter.removeItem(position);
                db.deletename(item);
                db.close();

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeDeleteCallback);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);
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
