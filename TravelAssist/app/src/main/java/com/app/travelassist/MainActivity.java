package com.app.travelassist;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.travelassist.database.ShopUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ShopListAdapter adapter;
    private RecyclerView moviesRecyclerView;
    private ProgressBar mProgressBar;
    private TextView emptyView;
    private List<ShopsList> shopsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = (TextView) findViewById(R.id.empty_view);
        moviesRecyclerView = (RecyclerView) findViewById(R.id.shops_list);
        moviesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        moviesRecyclerView.setLayoutManager(llm);
        shopsList = new ArrayList<ShopsList>();
    }

    private class LoadDataAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            shopsList = ShopUtil.getShopsList();
            adapter = new ShopListAdapter(getApplicationContext(), shopsList);
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
}
