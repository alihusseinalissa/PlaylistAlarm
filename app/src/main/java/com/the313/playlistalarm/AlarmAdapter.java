package com.the313.playlistalarm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ALI on 7/27/17.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    Context context;
    List<String> items;

    public AlarmAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }


    //this obviously returns the item count (you can mess around with this but usually you set it to the same size of your data)
    @Override
    public int getItemCount() {
        return items.size();
    }

    //this class is where you define your view and whre you bind them

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;

        // if you use butter knife here is where you put all the @BindView

        public ViewHolder(View itemView) {
            super(itemView);
            // and add a ButterKnife.bind(this,itemView); here too
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView.setTypeface(Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DroidKufi-Regular.ttf"));
/*
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    you can either pass the context through the constructor above or

//                    Intent intent = new Intent(context, Main2Activity.class);
//                    context.startActivity(intent);

                    you can get it from the view itself which is better if you don't have strings from resources
                    Intent in = new Intent(v.getContext(),Main2Activity.class);
                    v.getContext().startActivity(in);
                }
            });
            */
        }
    }
}