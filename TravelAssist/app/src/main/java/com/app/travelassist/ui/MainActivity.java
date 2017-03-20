package com.app.travelassist.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.travelassist.R;
import com.app.travelassist.model.MenuItem;
import com.app.travelassist.util.ShopListAdapter;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.database.ShopUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ShopListAdapter adapter;
    private RecyclerView moviesRecyclerView;
    private ProgressBar mProgressBar;
    private TextView emptyView;
    private List<ShopDetail> shopsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = (TextView) findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) findViewById(R.id.loading);
        moviesRecyclerView = (RecyclerView) findViewById(R.id.shops_list);
        moviesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        moviesRecyclerView.setLayoutManager(llm);
        shopsList = new ArrayList<ShopDetail>();
        putDummyData();
        putDummyItems();
        new LoadDataAsync().execute();
    }

    private class LoadDataAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            shopsList = ShopUtil.getShopsList();
            adapter = new ShopListAdapter(MainActivity.this,getApplicationContext(), shopsList);
            return null;

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            moviesRecyclerView.setAdapter(adapter);
            mProgressBar.setVisibility(View.GONE);
            if (shopsList.isEmpty()) {
                moviesRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                moviesRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    private void putDummyData(){
        List<ShopDetail> list=new ArrayList<>();
        ShopDetail shop1=new ShopDetail();
        shop1.setShopId("S001");
        shop1.setShopName("A2B");
        shop1.setShopCuisine("North-Indian,South-Indian,Punjabi");
        shop1.setDistance(5.5);
        shop1.setShopType("Restaurant");
        shop1.setShopRating("4.5");
        shop1.setShopStatus("Open");
        ShopDetail shop2=new ShopDetail();
        shop2.setShopId("S002");
        shop2.setShopName("Saravana Bhavan");
        shop2.setShopCuisine("South-Indian,Chettinadu");
        shop2.setDistance(3.0);
        shop2.setShopType("Restaurant");
        shop2.setShopRating("4.0");
        shop2.setShopStatus("Open");
        ShopDetail shop3=new ShopDetail();
        shop3.setShopId("S003");
        shop3.setShopName("Akshaya Bhavan");
        shop3.setShopCuisine("Classic,Chettinadu");
        shop3.setDistance(1.6);
        shop3.setShopType("Restaurant");
        shop3.setShopStatus("Closed");
        shop3.setShopRating("4.0");
        ShopDetail shop4=new ShopDetail();
        shop4.setShopId("S004");
        shop4.setShopName("Lakshmi Hotel");
        shop4.setShopCuisine("Punjabi,Bihari");
        shop4.setDistance(6.5);
        shop4.setShopType("Restaurant");
        shop4.setShopStatus("Open");
        shop4.setShopRating("4.7");
        ShopDetail shop5=new ShopDetail();
        shop5.setShopId("S005");
        shop5.setShopName("malika departmental");
        shop5.setShopCuisine("Grocery");
        shop5.setDistance(10.7);
        shop5.setShopType("Grocery");
        shop5.setShopStatus("Open");
        shop5.setShopRating("4.2");
        ShopDetail shop6=new ShopDetail();
        shop6.setShopId("S006");
        shop6.setShopName("A to Z departments");
        shop6.setShopCuisine("Classic,Chettinadu");
        shop6.setDistance(15.4);
        shop6.setShopType("Grocery");
        shop6.setShopStatus("Open");
        shop6.setShopRating("3.9");
        ShopDetail shop7=new ShopDetail();
        shop7.setShopId("S007");
        shop7.setShopName("Just Bake");
        shop7.setShopCuisine("Cakes, Pastry");
        shop7.setDistance(11.2);
        shop7.setShopType("Pastry");
        shop7.setShopStatus("Closed");
        shop7.setShopRating("4.9");
        ShopDetail shop8=new ShopDetail();
        shop8.setShopId("S008");
        shop8.setShopName("Ayengar Bakery");
        shop8.setShopCuisine("Tea, Coffee");
        shop8.setDistance(9.7);
        shop8.setShopType("Bakery");
        shop8.setShopStatus("Open");
        shop8.setShopRating("4.5");

        list.add(shop1);
        list.add(shop2);
        list.add(shop3);
        list.add(shop4);
        list.add(shop5);
        list.add(shop6);
        list.add(shop7);
        list.add(shop8);
        ShopUtil.addShopsList(list);
    }

    private void putDummyItems(){
        List<MenuItem> list=new ArrayList<>();
        MenuItem item1=new MenuItem();
        item1.setItemId("I001");
        item1.setItemName("Idly");
        item1.setItemCategory("Breakfast");
        item1.setItemPrice("Rs.20");
        item1.setShopId("S001");

        MenuItem item2=new MenuItem();
        item2.setItemId("I002");
        item2.setItemName("Dosa");
        item2.setItemCategory("Breakfast");
        item2.setItemPrice("Rs.30");
        item2.setShopId("S001");

        MenuItem item3=new MenuItem();
        item3.setItemId("I003");
        item3.setItemName("Vada");
        item3.setItemCategory("Breakfast");
        item3.setItemPrice("Rs.10");
        item3.setShopId("S001");

        MenuItem item4=new MenuItem();
        item4.setItemId("I004");
        item4.setItemName("Tandoori Roti");
        item4.setItemCategory("Roti");
        item4.setItemPrice("Rs.25");
        item4.setShopId("S002");

        MenuItem item5=new MenuItem();
        item5.setItemId("I005");
        item5.setItemName("Panner Butter Masala");
        item5.setItemCategory("Veg gravy");
        item5.setItemPrice("Rs.70");
        item5.setShopId("S002");

        MenuItem item6=new MenuItem();
        item6.setItemId("I006");
        item6.setItemName("Chicken Briyani");
        item6.setItemCategory("Briyani");
        item6.setItemPrice("Rs.140");
        item6.setShopId("S002");

        MenuItem item7=new MenuItem();
        item7.setItemId("I007");
        item7.setItemName("Gulab Jamun");
        item7.setItemCategory("Dessert");
        item7.setItemPrice("Rs.50");
        item7.setShopId("S002");

        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);


        ShopUtil.addItemsList(list);
    }
}
