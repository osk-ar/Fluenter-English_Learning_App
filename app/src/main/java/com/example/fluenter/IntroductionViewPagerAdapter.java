package com.example.fluenter;

import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class IntroductionViewPagerAdapter extends FragmentStateAdapter {

    private final List<String> titles;
    private final List<String> subTitles;
    private final List<Integer> imageResIds;

    public IntroductionViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> titles, List<String> subTitles, List<Integer> imageResIds) {
        super(fragmentActivity);
        this.titles = titles;
        this.subTitles = subTitles;
        this.imageResIds = imageResIds;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return IntroductionFragment.newInstance(titles.get(position), subTitles.get(position), imageResIds.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}

