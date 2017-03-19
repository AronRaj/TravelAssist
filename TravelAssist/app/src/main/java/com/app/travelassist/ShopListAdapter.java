package com.app.travelassist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.videoplayer.object.MoviesList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarokiax on 2/20/2017.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.SimpleViewHolder> {

    private static Context mContext;
    private static List<ShopsList> mData;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;


        public SimpleViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            title = (TextView) view.findViewById(R.id.language_text);

        }
    }

    public ShopListAdapter(Context context, List<ShopsList> data) {
        mContext = context;
        if (data != null)
            mData = new ArrayList<ShopsList>(data);
        else mData = new ArrayList<ShopsList>();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.movies_lang_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(mData.get(position).getShopName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
