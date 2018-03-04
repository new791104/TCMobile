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

import pllab.Activity.HRVdActivity;
import pllab.tcmobile.R;

/**
 * Created by cminer on 2018/2/28.
 */

public class detect_RecyclerViewAdapter extends RecyclerView.Adapter<detect_RecyclerViewAdapter.cardViewHolder> implements View.OnClickListener {

    private Context cContext;
    private CharSequence[] dataType_string;
    private int mPosition;


    @Override
    public void onClick(View view) {
        Log.e("deb", "onClick!");
        Intent intent = new Intent();
        switch (mPosition) {
            case 0:
                intent = new Intent(cContext, HRVdActivity.class);
                break;
            case 1:
                break;
        }

        cContext.startActivity(intent);
    }

    public static class cardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textView_cv;

        cardViewHolder(View itemView) {
            super(itemView);
            Log.e("deb", "###cardViewHolder###");
            cv = itemView.findViewById(R.id.detect_cv);
            textView_cv = itemView.findViewById(R.id.dataTitle_textView);
        }
    }

    /* constructor */
    public detect_RecyclerViewAdapter(Context context) {
        Log.e("deb", "###detect_RecyclerViewAdapter###");
        cContext = context;
        dataType_string = cContext.getResources().getStringArray(R.array.dataType);
        Log.e("deb", dataType_string[0].toString());
    }



    @Override
    public cardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("deb", "###onCreateViewHolder###");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_detect_card, parent, false);
        v.setOnClickListener(this);

        return new cardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(cardViewHolder holder, int position) {
        holder.textView_cv.setText(dataType_string[position]);
    }

    @Override
    public int getItemCount() {
        return dataType_string.length;
    }

}
