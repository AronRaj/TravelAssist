package com.app.travelassist.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.travelassist.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelInfoFragment extends Fragment {

    private static final String SHOP_ID = "shopid";

    private String shopId;
    private View mRootView;


    public HotelInfoFragment() {
        // Required empty public constructor
    }

    public static HotelInfoFragment newInstance(String shopId) {
        HotelInfoFragment fragment = new HotelInfoFragment();
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
        mRootView= inflater.inflate(R.layout.fragment_hotel_info, container, false);

        return mRootView;
    }

}
