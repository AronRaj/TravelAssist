package com.app.travelassist.ui;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.travelassist.R;
import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.ShopDetail;

import java.util.ArrayList;
import java.util.List;


public class HotelFragment extends Fragment {

    private static final String ARG_SHOP_ID = "shop_id";

    private String shopId;
    private View mRootView;
    private ImageView hotelImage;
    private RatingBar hotelRating;
    private TextView hotelName;
    private TextView hotelCuisine;
    private TextView hotelMapText;
    private TextView hotelInfoText;
    private ListView hotelMenuList;

    List<String> list;


    public HotelFragment() {
        // Required empty public constructor
    }

    public static HotelFragment newInstance(String shopId) {
        HotelFragment fragment = new HotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOP_ID, shopId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopId = getArguments().getString(ARG_SHOP_ID);
        }
        list = ShopUtil.getCategoriesForShop(shopId);
        /*String[] values = new String[]{"South-Indian", "Punjabi", "Popular",
                "Breakfast", "Dinner", "Snacks", "Deserts", "Beverages"};

        list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_hotel, container, false);
        hotelImage = (ImageView) mRootView.findViewById(R.id.hotel_image);
        hotelName = (TextView) mRootView.findViewById(R.id.hotel_name);
        hotelCuisine = (TextView) mRootView.findViewById(R.id.hotel_cuisine);
        hotelMapText = (TextView) mRootView.findViewById(R.id.hotel_map_text);
        hotelMapText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = MapViewFragment.newInstance(shopId);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.slide_left_enter, R.animator.slide_left_exit,R.animator.slide_right_enter,R.animator.slide_right_exit);
                fragmentTransaction.replace(R.id.hotel_menu, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        hotelInfoText = (TextView) mRootView.findViewById(R.id.hotel_info_text);
        hotelInfoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = HotelInfoFragment.newInstance("");
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.slide_left_enter, R.animator.slide_left_exit,R.animator.slide_right_enter,R.animator.slide_right_exit);
                fragmentTransaction.replace(R.id.hotel_menu, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        hotelRating = (RatingBar) mRootView.findViewById(R.id.hotel_rating);
        hotelMenuList = (ListView) mRootView.findViewById(R.id.hotel_menu_list);
        populateShopData();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        hotelMenuList.setAdapter(adapter);
        hotelMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                final String item = (String) parent.getItemAtPosition(position);
                Log.d("Shop", "Item : " + item);
                Fragment fragment = ItemFragment.newInstance(shopId,item);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.slide_left_enter, R.animator.slide_left_exit,R.animator.slide_right_enter,R.animator.slide_right_exit);
                fragmentTransaction.replace(R.id.hotel_menu, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return mRootView;
    }

    private void populateShopData() {
        ShopDetail shop = ShopUtil.getShopInfo(shopId);
        if (null != shop) {
            hotelName.setText(shop.getShopName());
            hotelCuisine.setText(shop.getShopCuisine());
            hotelRating.setRating(Float.parseFloat(shop.getShopRating()));
        }

    }


}
