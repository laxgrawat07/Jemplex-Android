package com.jemplex.jemplex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jemplex.jemplex.response.AppMenu;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FATMenuRecyclerViewAdapter extends RecyclerView.Adapter<FATMenuRecyclerViewAdapter.ViewHolder> {

    private final int value;
    private List<String> mValues;
    private List<Integer> mImages;


    public interface MyListener {
        public void callbackOnClick();
    }

    MenuViewModel viewModel;
    private MyListener listener;

    public FATMenuRecyclerViewAdapter(ArrayList<String> items, MyListener listener, int value, ArrayList<Integer> parentListImages) {
        mValues = items;
        this.listener = listener;
        this.value = value;
        this.mImages = parentListImages;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fat_menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
        holder.txtMenu.setText(mValues.get(position));

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.android)
                .error(R.drawable.android);

<<<<<<< HEAD
=======

>>>>>>> c009a9b47588c33fce5c7f9ae6d62937d4d89c3d
        Glide.with(holder.context).load(mImages.get(position)).apply(options).into(holder.imgMenu);
        //holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final ImageView imgMenu;
        public final TextView txtMenu;
        public AppMenu mItem;
        private final Context context;

        public ViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            mView = view;
            imgMenu = (ImageView) view.findViewById(R.id.imgMenu);
            txtMenu = (TextView) view.findViewById(R.id.txtMenu);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (value == 1) listener.callbackOnClick();

            /*AppCompatActivity activity = (AppCompatActivity) v.getContext();
            FATMenuFragment myFragment = new FATMenuFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, myFragment).addToBackStack(null).commit();*/


            /* viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MenuViewModel.class);*/
           /*String StrMenu = txtMenu.getText().toString();/*
            List<AppMenu> Menu = mValues.stream()
                    .filter((p) -> p.menuName.equals(StrMenu))
                    .collect(Collectors.toList());
                        //Toast.makeText(getActivity(),"Login Button Clicked", Toast.LENGTH_SHORT).show();
                       /* viewModel.NavigationComponentClicked("Menu ");
            mValues=Menu;
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new FATMenuFragment())
                    .commit();

            final Intent intent = new Intent(context, AssetTagging.class);
            Bundle bundle = new Bundle();
            ArrayList<AppMenu> arMenu = new ArrayList<>(mValues.size());
            arMenu.addAll(mValues);
            bundle.putParcelableArrayList("valuesArray", arMenu);
            intent.putExtras(bundle);
            /*context.startActivity(intent);*/


        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + txtMenu.getText() + "'";
        }
    }
}