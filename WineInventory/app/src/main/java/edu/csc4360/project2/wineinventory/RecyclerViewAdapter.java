package edu.csc4360.project2.wineinventory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> wineNames;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView wineName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wineName = itemView.findViewById(R.id.wineName);

        }
    }


    public RecyclerViewAdapter(ArrayList<String> wineNames) {
        this.wineNames = wineNames;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wine, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.wineName.setText(wineNames.get(position));

    }

    @Override
    public int getItemCount() {

        return wineNames.size();
    }

    public void removeItem(int position) {

        wineNames.remove(position);
        notifyItemRemoved(position);

    }

    public ArrayList<String> getData() {

        return wineNames;
    }

}
