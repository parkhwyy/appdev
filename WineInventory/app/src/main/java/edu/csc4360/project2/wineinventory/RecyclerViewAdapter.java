package edu.csc4360.project2.wineinventory;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> wineNames;

    public RecyclerViewAdapter(ArrayList<String> wineNames) {
        this.wineNames = wineNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wine, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.wineName.setText(wineNames.get(position));

    }

    @Override
    public int getItemCount() {
        return wineNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView wineName;
        FrameLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wineName = itemView.findViewById(R.id.wineName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }




}
