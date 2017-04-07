package com.app.travelassist.ui;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.app.travelassist.R;
import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.Item;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.util.ItemAdapter;
import com.app.travelassist.util.ShopListAdapter;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment {

    private static final String SHOP_ID = "shop_id";
    private static final String CATEGORY = "category";

    private View mRootView;
    private String shopId;
    private String category;
    ArrayList<Item> listData;
    private RecyclerView itemsList;
    private ItemAdapter adapter;


    public ItemFragment() {
        // Required empty public constructor
    }


    public static ItemFragment newInstance(String shopId, String category) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(SHOP_ID, shopId);
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopId = getArguments().getString(SHOP_ID);
            category = getArguments().getString(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView= inflater.inflate(R.layout.fragment_item, container, false);
        itemsList = (RecyclerView) mRootView.findViewById(R.id.items_list);
        listData = ShopUtil.getItemsForShop(shopId,category);
        itemsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        itemsList.setLayoutManager(llm);
        adapter = new ItemAdapter(getActivity(), getActivity().getApplicationContext(), listData);
        itemsList.setAdapter(adapter);
        return mRootView;
    }


}
