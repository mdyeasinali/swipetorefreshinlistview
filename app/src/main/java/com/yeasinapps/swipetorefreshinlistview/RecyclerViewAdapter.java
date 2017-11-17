package com.yeasinapps.swipetorefreshinlistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 11/13/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>{

    private List<Movie> moviesList;
    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context,List<Movie> moviesList){
        layoutInflater = LayoutInflater.from(context);
        this.moviesList = moviesList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.layout_item, parent, false);
        return new ItemHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.setItemText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(ItemHolder item, int position);
    }

//    public void add(int location, String iString){
//        itemsList.add(location, iString);
//        notifyItemInserted(location);
//    }
//
//    public void set(int location, String iString){
//        itemsList.set(location, iString);
//        notifyItemChanged(location);
//    }
//
//    public void clear(){
//        itemsList.clear();
//        notifyDataSetChanged();
//    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private RecyclerViewAdapter parent;
        TextView textItemText;

        public ItemHolder(View itemView, RecyclerViewAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.parent = parent;
            textItemText = (TextView) itemView.findViewById(R.id.item_text);
        }

        public void setItemText(CharSequence itemString){
            textItemText.setText(itemString);
        }

        public CharSequence getItemText(){
            return textItemText.getText();
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if(listener != null){
                listener.onItemClick(this, getAdapterPosition());
            }
        }
    }
}