package com.app.travelassist.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.travelassist.R;
import com.app.travelassist.model.HotelTime;
import com.app.travelassist.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intel on 3/31/17.
 */

public class OperationTimeAdapter extends RecyclerView.Adapter<OperationTimeAdapter.SimpleViewHolder>{

    private static Context mContext;
    private static Activity activity;
    private static List<HotelTime> mData;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView day;
        public final TextView fromTime;
        public final TextView toTime;


        public SimpleViewHolder(View view) {
            super(view);
            mContext = itemView.getContext();
            day = (TextView) view.findViewById(R.id.day);
            fromTime = (TextView) view.findViewById(R.id.from_time);
            toTime = (TextView) view.findViewById(R.id.to_time);

        }

    }

    public OperationTimeAdapter(Activity acyivity, Context context, List<HotelTime> data) {
        mContext = context;
        activity=acyivity;
        if (data != null)
            mData = new ArrayList<HotelTime>(data);
        else mData = new ArrayList<HotelTime>();
    }

    public OperationTimeAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.time_list_item, parent, false);
        return new OperationTimeAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OperationTimeAdapter.SimpleViewHolder holder, final int position) {
        holder.day.setText(mData.get(position).getDay());
        holder.fromTime.setText("From : "+mData.get(position).getFromTime());
        holder.toTime.setText("To : "+mData.get(position).getToTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
