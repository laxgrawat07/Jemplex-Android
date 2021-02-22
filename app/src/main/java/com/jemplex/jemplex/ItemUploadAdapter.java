package com.jemplex.jemplex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jemplex.jemplex.response.AppMenu;

import java.util.ArrayList;
import java.util.List;

public class ItemUploadAdapter extends RecyclerView.Adapter<ItemUploadAdapter.ViewHolder>{

    private List<AppMenu> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CheckBox cbUploadHeader;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            cbUploadHeader = (CheckBox) v.findViewById(R.id.cbUploadItem);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemUploadAdapter(List<AppMenu> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemUploadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.uplad_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position).toString();
        holder.cbUploadHeader.setText(values.get(position).menuName);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
