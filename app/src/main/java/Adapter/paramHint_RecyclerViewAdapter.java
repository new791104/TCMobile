package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Fragments.HistogramFragment;
import Global.GV;
import pllab.Activity.HRVdActivity;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2018/3/22.
 */

public class paramHint_RecyclerViewAdapter extends RecyclerView.Adapter<paramHint_RecyclerViewAdapter.viewHolder> {
    private HistogramFragment histogramFragment;

    public paramHint_RecyclerViewAdapter(HistogramFragment histogramFragment) {
        this.histogramFragment = histogramFragment;
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemTV;
        HistogramFragment histogramFragment;
        public viewHolder(TextView itemView, HistogramFragment histo) {
            super(itemView);
            itemTV = itemView;
            histogramFragment = histo;
        }

        @Override
        public void onClick(View v) {
            histogramFragment.setPosition(this.getLayoutPosition());
        }
    }

    @Override
    public paramHint_RecyclerViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        viewHolder vh = new viewHolder(tv, histogramFragment);
        return vh;
    }

    @Override
    public void onBindViewHolder(paramHint_RecyclerViewAdapter.viewHolder holder, int position) {
        holder.itemTV.setText(GV.tablelist.getDataFields()[position]);
    }

    @Override
    public int getItemCount() {
        return GV.tablelist.getDataFields().length;
    }

}
