package com.barreto.exe.gochat.activities;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.barreto.exe.gochat.activities.ui.main.SectionsPagerAdapter;
import com.barreto.exe.gochat.databinding.ActivitySelectCreationBinding;

public class SelectCreationActivity extends AppCompatActivity {
    private ActivitySelectCreationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySelectCreationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}