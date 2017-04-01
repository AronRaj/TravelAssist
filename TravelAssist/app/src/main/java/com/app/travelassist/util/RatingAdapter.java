package com.app.travelassist.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.travelassist.R;
import com.app.travelassist.model.Rating;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.ui.HotelFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intel on 3/31/17.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.SimpleViewHolder>{

    private static Context mContext;
    private static Activity activity;
    private static List<Rating> mData;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final RatingBar shopRating;
        public final TextView comment;


        public SimpleViewHolder(View view) {
            super(view);
            mContext = itemView.getContext();
            comment = (TextView) view.findViewById(R.id.rating_comment);
            shopRating = (RatingBar) view.findViewById(R.id.rating_item);

        }

    }

    public RatingAdapter(Activity acyivity,Context context, List<Rating> data) {
        mContext = context;
        activity=acyivity;
        if (data != null)
            mData = new ArrayList<Rating>(data);
        else mData = new ArrayList<Rating>();
    }

    public RatingAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.rating_list_item, parent, false);
        return new RatingAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingAdapter.SimpleViewHolder holder, final int position) {
        holder.shopRating.setRating(mData.get(position).getRating());
        holder.comment.setText("Type : "+mData.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
