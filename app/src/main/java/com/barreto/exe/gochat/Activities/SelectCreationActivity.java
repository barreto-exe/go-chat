package com.barreto.exe.gochat.activities;

import android.os.Bundle;

import com.barreto.exe.gochat.R;
import com.barreto.exe.gochat.databinding.PageCreateChatBinding;
import com.barreto.exe.gochat.databinding.PageJoinChatBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.barreto.exe.gochat.activities.ui.main.SectionsPagerAdapter;
import com.barreto.exe.gochat.databinding.ActivitySelectCreationBinding;

public class SelectCreationActivity extends AppCompatActivity {

    private ActivitySelectCreationBinding binding;
    private PageCreateChatBinding pageCreateChatBinding;
    private PageJoinChatBinding pageJoinChatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageCreateChatBinding = PageCreateChatBinding.inflate(getLayoutInflater());
        pageJoinChatBinding = PageJoinChatBinding.inflate(getLayoutInflater());
        binding = ActivitySelectCreationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}