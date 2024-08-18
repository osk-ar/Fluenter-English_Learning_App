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

public class ChaptersViewPagerAdapter extends FragmentStateAdapter {

    private final List<String> titles;
    private final List<String> subTitles;
    ChaptersData CD = new ChaptersData();

    public ChaptersViewPagerAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
        this.titles = CD.chapterTitles;
        this.subTitles = CD.chapterSubTitles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return ChaptersFragment.newInstance(titles.get(position), subTitles.get(position), CD.numbers);
            case 2:
                return ChaptersFragment.newInstance(titles.get(position), subTitles.get(position), CD.colors);
            case 3:
                return ChaptersFragment.newInstance(titles.get(position), subTitles.get(position), CD.family);
            default:
                return ChaptersFragment.newInstance(titles.get(position), subTitles.get(position), CD.letters);

        }

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
