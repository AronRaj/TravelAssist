package com.app.travelassist.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;


import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.app.travelassist.R;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.ui.HotelFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarokiax on 2/20/2017.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.SimpleViewHolder> {

    private static Context mContext;
    private static Activity activity;
    private static List<ShopDetail> mData;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView shopName;
        public final TextView shopType;
        public final TextView shopCuisine;
        public final TextView shopDistance;
        public final TextView shopStatus;
        public final RatingBar shopRating;
        public final ImageView shopImage;


        public SimpleViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mContext = itemView.getContext();
            shopName = (TextView) view.findViewById(R.id.shop_name);
            shopType = (TextView) view.findViewById(R.id.shop_type);
            shopCuisine = (TextView) view.findViewById(R.id.shop_cuisine);
            shopDistance = (TextView) view.findViewById(R.id.shop_distance);
            shopStatus = (TextView) view.findViewById(R.id.shop_status);
            shopRating = (RatingBar) view.findViewById(R.id.shop_rating);
            shopImage=(ImageView)view.findViewById(R.id.shopicon);

        }

        @Override
        public void onClick(View view) {
            Log.d("Shop","Selected Shop ::"+mData.get(getPosition()).getShopId());
            Fragment fragment = HotelFragment.newInstance(mData.get(getPosition()).getShopId());
            FragmentManager fragmentManager = ((Activity) activity).getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_left_enter, R.animator.slide_left_exit,R.animator.slide_right_enter,R.animator.slide_right_exit);
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public ShopListAdapter(Activity acyivity,Context context, List<ShopDetail> data) {
        mContext = context;
        activity=acyivity;
        if (data != null)
            mData = new ArrayList<ShopDetail>(data);
        else mData = new ArrayList<ShopDetail>();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.shop_list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.shopName.setText(mData.get(position).getShopName());
        holder.shopType.setText("Type : "+mData.get(position).getShopType());
        holder.shopCuisine.setText(mData.get(position).getShopCuisine());
        holder.shopDistance.setText(String.valueOf(mData.get(position).getDistance())+" Km");
        holder.shopRating.setRating(Float.parseFloat(mData.get(position).getShopRating()));
        holder.shopStatus.setText("Status : "+mData.get(position).getShopStatus());
        Picasso.with(mContext).load(mData.get(position).getShopImageUrl()).into(holder.shopImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
