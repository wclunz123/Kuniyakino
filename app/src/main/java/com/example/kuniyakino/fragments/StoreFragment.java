package com.example.kuniyakino.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kuniyakino.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Setup the store location button to intent to Google Map Application
        Button locationBtn1 = (Button) getView().findViewById(R.id.mallLocation1);
        locationBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hardcoded coordinates used to direct the Google Map pinpoint coordinates
                Uri uri = Uri.parse("geo:1.2646177235390001,103.82223928388068?q=VivoCity");
                openMap(uri);
            }
        });

        // Setup the store location button to intent to Google Map Application
        Button locationBtn2 = (Button) getView().findViewById(R.id.mallLocation2);
        locationBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hardcoded coordinates used to direct the Google Map pinpoint coordinates
                Uri uri = Uri.parse("geo:1.2942566993396947,103.85315802618969?q=RafflesCity");
                openMap(uri);
            }
        });

        // Setup the store location button to intent to Google Map Application
        Button locationBtn3 = (Button) getView().findViewById(R.id.mallLocation3);
        locationBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hardcoded coordinates used to direct the Google Map pinpoint coordinates
                Uri uri = Uri.parse("geo:1.3925426834215249,103.8950285423295?q=Compassone");
                openMap(uri);
            }
        });
    }

    // Method used to intent to the Google Map application of the device
    private void openMap(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }


}