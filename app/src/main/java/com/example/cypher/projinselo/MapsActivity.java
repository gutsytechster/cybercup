package com.example.cypher.projinselo;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng myplace1 = new LatLng(24.8109297,83.2925216);
        mMap.addMarker(new MarkerOptions().position(myplace1).title("Guru Nanak Institute of Technology"));
        float zoomLevel = 12.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myplace1, zoomLevel));


        LatLng myplace2 = new LatLng(24.8157977,83.2825156);
        mMap.addMarker(new MarkerOptions().position(myplace2).title("Heath Guard Medical Store"));


        LatLng myplace3 = new LatLng(24.8169157,83.2835356);
        mMap.addMarker(new MarkerOptions().position(myplace3).title("Vishal Medical Store"));

        LatLng myplace4 = new LatLng(24.8159497,83.2895526);
        mMap.addMarker(new MarkerOptions().position(myplace4).title("Kaushik Medical Store"));

        LatLng myplace5 = new LatLng(24.8157497,83.2795526);
        mMap.addMarker(new MarkerOptions().position(myplace5).title("pratik Medical Store"));

        LatLng myplace6 = new LatLng(24.8169497,83.2995526);
        mMap.addMarker(new MarkerOptions().position(myplace6).title("Prajit Medical Store"));


        LatLng myplace7 = new LatLng(24.8259497,83.2795526);
        mMap.addMarker(new MarkerOptions().position(myplace7).title("prathna Medical Store"));


        LatLng myplace8 = new LatLng(24.8259497,83.52795526);
        mMap.addMarker(new MarkerOptions().position(myplace8).title("Arindam Medical Store"));

    }
}

