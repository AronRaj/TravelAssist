package com.app.travelassist.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.travelassist.R;
import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.volley.VolleyUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelInfoFragment extends Fragment {

    private static final String TAG = "HotelInfoFragment";

    private static final String SHOP_ID = "shopid";
    private String shopId;
    private View mRootView;

    private TextView mShopMobile;
    private TextView mShopEmail;
    private TextView mShopRatingText;
    private TextView mShopTotalRated;
    private RatingBar mShopRatingBar;
    private RatingBar mShopCustomerRatingBar;
    private EditText mShopCustomerComments;
    private Button mRatingSubmitButton;


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
        mRootView = inflater.inflate(R.layout.fragment_hotel_info, container, false);
        mShopMobile = (TextView) mRootView.findViewById(R.id.mobilenumber);
        mShopEmail = (TextView) mRootView.findViewById(R.id.email);
        mShopTotalRated = (TextView) mRootView.findViewById(R.id.total_ratings);
        mShopRatingText = (TextView) mRootView.findViewById(R.id.shop_rating);
        mShopRatingBar = (RatingBar) mRootView.findViewById(R.id.shop_rating_bar);
        mShopCustomerRatingBar = (RatingBar) mRootView.findViewById(R.id.customer_rating_bar_shop);
        mShopCustomerComments = (EditText) mRootView.findViewById(R.id.customer_comments);
        mRatingSubmitButton=(Button)mRootView.findViewById(R.id.rating_submit_button);
        mRatingSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float customerRating=mShopCustomerRatingBar.getRating();
                String customerComments=mShopCustomerComments.getText().toString();
                VolleyUtil.submitShopRating(shopId,customerRating,customerComments);
            }
        });
        populateShopData();
        return mRootView;
    }

    private void populateShopData() {
        ShopDetail shop = ShopUtil.getShopInfo(shopId);
        if (null != shop) {
            mShopMobile.setText(shop.getShopMobile());
            mShopTotalRated.setText(shop.getShopTotalRated()+" Ratings");
            mShopRatingText.setText(shop.getShopRating());
            mShopRatingBar.setRating(Float.parseFloat(shop.getShopRating()));
        }

    }

}
