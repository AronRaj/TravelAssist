package com.app.travelassist.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.travelassist.R;
import com.app.travelassist.model.Item;
import com.app.travelassist.model.Rating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intel on 3/31/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.SimpleViewHolder>{

    private static Context mContext;
    private static Activity activity;
    private static List<Item> mData;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView itemName;
        public final TextView itemPrice;


        public SimpleViewHolder(View view) {
            super(view);
            mContext = itemView.getContext();
            itemName = (TextView) view.findViewById(R.id.item_name);
            itemPrice = (TextView) view.findViewById(R.id.item_price);

        }

    }

    public ItemAdapter(Activity acyivity, Context context, List<Item> data) {
        mContext = context;
        activity=acyivity;
        if (data != null)
            mData = new ArrayList<Item>(data);
        else mData = new ArrayList<Item>();
    }

    public ItemAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.rating_list_item, parent, false);
        return new ItemAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.SimpleViewHolder holder, final int position) {
        holder.itemName.setText(mData.get(position).getItemName());
        holder.itemPrice.setText("Rs : "+mData.get(position).getItemPrice());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
