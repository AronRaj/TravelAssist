package com.app.travelassist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.travelassist.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopReviewsFragment extends Fragment {
    private static final String SHOP_ID = "shopid";

    private String shopId;


    public ShopReviewsFragment() {
        // Required empty public constructor
    }

    public static ShopReviewsFragment newInstance(String shopId) {
        ShopReviewsFragment fragment = new ShopReviewsFragment();
        Bundle args = new Bundle();
        args.putString(SHOP_ID, shopId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopId = getArguments().getString(SHOP_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_reviews, container, false);
    }

}
