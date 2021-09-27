package com.example.kuniyakino.fragments;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuniyakino.adapter.ImageAdapter;
import com.example.kuniyakino.R;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Display animation transition of images in the home page
        ImageView imgSlideshow = (ImageView)getView().findViewById(R.id.backgroundAnimation);
        AnimationDrawable animationDrawable = (AnimationDrawable) imgSlideshow.getDrawable();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();

        // Change the welcome title styling
        TextView welcomeView = (TextView)getView().findViewById(R.id.welcomeText);
        welcomeView.setBackgroundColor(Color.argb(180, 105, 105, 105));

        // Setup the viewpager for image sliders
        ViewPager viewPager = getView().findViewById(R.id.homePageViewPager);
        ImageAdapter adapter = new ImageAdapter(getContext());
        viewPager.setAdapter(adapter);
    }

}

