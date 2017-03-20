package com.app.travelassist.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.travelassist.R;
import com.app.travelassist.database.ShopUtil;

import java.util.List;


public class ItemFragment extends Fragment {

    private static final String SHOP_ID = "shop_id";
    private static final String CATEGORY = "category";

    private View mRootView;
    private String shopId;
    private String category;
    private ListView itemsList;
    List<String> listData;


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
        itemsList = (ListView) mRootView.findViewById(R.id.items_list);
        listData = ShopUtil.getItemsForShop(shopId,category);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listData);
        itemsList.setAdapter(adapter);
        return mRootView;
    }


}
