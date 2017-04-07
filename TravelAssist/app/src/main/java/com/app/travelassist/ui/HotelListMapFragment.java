package com.app.travelassist.ui;


import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.travelassist.R;
import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.ShopDetail;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class HotelListMapFragment extends Fragment {

    private static final String TAG="HotelListMapFragment";


    View mRootView;
    MapView mMapView;
    private GoogleMap googleMap;
    List<ShopDetail> shopsList;


    public HotelListMapFragment() {
        // Required empty public constructor
    }


    public static HotelListMapFragment newInstance() {
        HotelListMapFragment fragment = new HotelListMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView= inflater.inflate(R.layout.fragment_hotel_list_map, container, false);
        mMapView = (MapView) mRootView.findViewById(R.id.mapListView);
        mMapView.onCreate(savedInstanceState);
        addMapLayout();
        return mRootView;
    }

    private void addMapLayout(){
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                Location currentLocation=ShopUtil.getCurrentLocationFromDB();
                // For dropping a marker at a point on the Map
                if(null!=currentLocation) {
                    LatLng sydney = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(sydney).title(getString(R.string.my_location_text)));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                populateShopsOnMap();
            }
        });
    }

    private void populateShopsOnMap() {
        shopsList = ShopUtil.getShopsList();
        if (null != shopsList) {
            for (ShopDetail shop : shopsList) {
                LatLng ll = new LatLng(shop.getShopLatitude(), shop.getShopLongitude());
                //drawMarker(ll);
                BitmapDescriptor bitmapMarker = null;
                switch (shop.getShopType()) {
                    case "Restaurant":
                        bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                        Log.i(TAG, "RED");
                        break;
                    case "Grocery":
                        bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                        Log.i(TAG, "GREEN");
                        break;
                        /*case 2:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                            Log.i(TAG, "ORANGE");
                            break;*/
                    default:
                        bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                        Log.i(TAG, "DEFAULT");
                        break;
                }
                googleMap.addMarker(new MarkerOptions().position(ll).title(shop.getShopName())
                        .snippet(shop.getShopStatus()+"/n"+shop.getShopRating()).icon(bitmapMarker));

                Log.i(TAG, "Shop  " + shop.getShopName() + "  was added ");
            }
        }

    }

}
