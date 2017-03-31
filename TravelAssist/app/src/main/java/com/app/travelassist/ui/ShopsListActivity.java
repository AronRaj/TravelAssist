package com.app.travelassist.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.travelassist.R;
import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.util.LocationManager;
import com.app.travelassist.util.ShopListAdapter;
import com.app.travelassist.volley.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

public class ShopsListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ShopListAdapter adapter;
    private RecyclerView moviesRecyclerView;
    private ProgressBar mProgressBar;
    private TextView emptyView;
    private List<ShopDetail> shopsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        emptyView = (TextView) findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) findViewById(R.id.loading);
        moviesRecyclerView = (RecyclerView) findViewById(R.id.shops_list);
        moviesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        moviesRecyclerView.setLayoutManager(llm);
        shopsList = new ArrayList<ShopDetail>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle("Travel");
        Intent intent = new Intent(this, LocationManager.class);
        startService(intent);
        putDummyData();
        putDummyItems();
        new LoadDataAsync().execute();
    }

    private class LoadDataAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            shopsList = ShopUtil.getShopsList();
            adapter = new ShopListAdapter(ShopsListActivity.this,getApplicationContext(), shopsList);
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
        //VolleyUtil.getShopsWithLatLng(12.9913,77.6874,"restaurant","500");
        List<ShopDetail> list=new ArrayList<>();
        ShopDetail shop1=new ShopDetail();
        shop1.setShopId("S001");
        shop1.setShopName("A2B");
        shop1.setShopCuisine("North-Indian,South-Indian,Punjabi");
        shop1.setDistance(5.5);
        shop1.setShopType("Restaurant");
        shop1.setShopRating("4.5");
        shop1.setShopTotalRated("44");
        shop1.setShopStatus("Open");
        shop1.setShopAddress("204, Akkamma Complex, Outer Ring Road, Mahadevapura, Near More Mega Store, Bengaluru");
        shop1.setShopMobile("12345");
        shop1.setShopLatitude(12.991131);
        shop1.setShopLongitude(77.6878);
        ShopDetail shop2=new ShopDetail();
        shop2.setShopId("S002");
        shop2.setShopName("Saravana Bhavan");
        shop2.setShopCuisine("South-Indian,Chettinadu");
        shop2.setDistance(3.0);
        shop2.setShopType("Restaurant");
        shop2.setShopRating("4.0");
        shop2.setShopTotalRated("64");
        shop2.setShopStatus("Open");
        shop2.setShopAddress("#G-28, Near Dominos Pizza,, Mahadevapura, Bengaluru");
        shop2.setShopMobile("12345");
        shop2.setShopLatitude(12.99128);
        shop2.setShopLongitude(77.68736699999999);
        ShopDetail shop3=new ShopDetail();
        shop3.setShopId("S003");
        shop3.setShopName("Akshaya Bhavan");
        shop3.setShopCuisine("Classic,Chettinadu");
        shop3.setDistance(1.6);
        shop3.setShopType("Restaurant");
        shop3.setShopStatus("Closed");
        shop3.setShopRating("4.0");
        shop3.setShopTotalRated("164");
        shop3.setShopAddress("Saraswathi Nagar, Mahadevapura, Bengaluru");
        shop3.setShopMobile("12345");
        shop3.setShopLatitude(12.9903981);
        shop3.setShopLongitude(77.68842189999999);
        ShopDetail shop4=new ShopDetail();
        shop4.setShopId("S004");
        shop4.setShopName("Lakshmi Hotel");
        shop4.setShopCuisine("Punjabi,Bihari");
        shop4.setDistance(6.5);
        shop4.setShopType("Restaurant");
        shop4.setShopStatus("Open");
        shop4.setShopRating("4.7");
        shop4.setShopTotalRated("20");
        shop4.setShopAddress("35, Maheshwaramma Temple Road, Behind More Mega Store, Maheswari Nagar, Bengaluru");
        shop4.setShopMobile("12345");
        shop4.setShopLatitude(12.9917923);
        shop4.setShopLongitude(77.6905379);
        ShopDetail shop5=new ShopDetail();
        shop5.setShopId("S005");
        shop5.setShopName("malika departmental");
        shop5.setShopCuisine("Grocery");
        shop5.setDistance(10.7);
        shop5.setShopType("Grocery");
        shop5.setShopStatus("Open");
        shop5.setShopRating("4.2");
        shop5.setShopTotalRated("103");
        shop5.setShopAddress("Saraswathi Nagar, B Narayanapura, Mahadevapura, Bengaluru");
        shop5.setShopMobile("12345");
        shop5.setShopLatitude(12.991292);
        shop5.setShopLongitude(77.6877507);
        ShopDetail shop6=new ShopDetail();
        shop6.setShopId("S006");
        shop6.setShopName("A to Z departments");
        shop6.setShopCuisine("Classic,Chettinadu");
        shop6.setDistance(15.4);
        shop6.setShopType("Grocery");
        shop6.setShopStatus("Open");
        shop6.setShopRating("3.9");
        shop6.setShopTotalRated("170");
        shop6.setShopAddress("Mahadevpura Main Road, Saraswathi Nagar, B Narayanapura, Mahadevapura, Bengaluru");
        shop6.setShopMobile("12345");
        shop6.setShopLatitude(12.9914844);
        shop6.setShopLongitude(77.6877976);
        ShopDetail shop7=new ShopDetail();
        shop7.setShopId("S007");
        shop7.setShopName("Just Bake");
        shop7.setShopCuisine("Cakes, Pastry");
        shop7.setDistance(11.2);
        shop7.setShopType("Pastry");
        shop7.setShopStatus("Closed");
        shop7.setShopRating("4.9");
        shop7.setShopTotalRated("33");
        shop7.setShopAddress("Near Mega More Retail, Mahadevpura Ring Road, Bengaluru");
        shop7.setShopMobile("12345");
        shop7.setShopLatitude(12.990971);
        shop7.setShopLongitude(77.68788099999999);
        ShopDetail shop8=new ShopDetail();
        shop8.setShopId("S008");
        shop8.setShopName("Ayengar Bakery");
        shop8.setShopCuisine("Tea, Coffee");
        shop8.setDistance(9.7);
        shop8.setShopType("Bakery");
        shop8.setShopStatus("Open");
        shop8.setShopRating("4.5");
        shop8.setShopTotalRated("96");
        shop8.setShopAddress("Iris Inn, 21, 22/3, Outer Ring Road, Mahadevpura, Near Marathahalli, Outer Ring Road");
        shop8.setShopMobile("12345");
        shop8.setShopLatitude(12.991084);
        shop8.setShopLongitude(77.6880572);

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
        List<com.app.travelassist.model.MenuItem> list=new ArrayList<>();
        com.app.travelassist.model.MenuItem item1=new com.app.travelassist.model.MenuItem();
        item1.setItemId("I001");
        item1.setItemName("Idly");
        item1.setItemCategory("Breakfast");
        item1.setItemPrice("Rs.20");
        item1.setShopId("S001");

        com.app.travelassist.model.MenuItem item2=new com.app.travelassist.model.MenuItem();
        item2.setItemId("I002");
        item2.setItemName("Dosa");
        item2.setItemCategory("Breakfast");
        item2.setItemPrice("Rs.30");
        item2.setShopId("S001");

        com.app.travelassist.model.MenuItem item3=new com.app.travelassist.model.MenuItem();
        item3.setItemId("I003");
        item3.setItemName("Vada");
        item3.setItemCategory("Breakfast");
        item3.setItemPrice("Rs.10");
        item3.setShopId("S001");

        com.app.travelassist.model.MenuItem item4=new com.app.travelassist.model.MenuItem();
        item4.setItemId("I004");
        item4.setItemName("Tandoori Roti");
        item4.setItemCategory("Roti");
        item4.setItemPrice("Rs.25");
        item4.setShopId("S002");

        com.app.travelassist.model.MenuItem item5=new com.app.travelassist.model.MenuItem();
        item5.setItemId("I005");
        item5.setItemName("Panner Butter Masala");
        item5.setItemCategory("Veg gravy");
        item5.setItemPrice("Rs.70");
        item5.setShopId("S002");

        com.app.travelassist.model.MenuItem item6=new com.app.travelassist.model.MenuItem();
        item6.setItemId("I006");
        item6.setItemName("Chicken Briyani");
        item6.setItemCategory("Briyani");
        item6.setItemPrice("Rs.140");
        item6.setShopId("S002");

        com.app.travelassist.model.MenuItem item7=new com.app.travelassist.model.MenuItem();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
