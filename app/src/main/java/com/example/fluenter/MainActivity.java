package com.example.fluenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 VP;
    Button Next;
    Button Previous;
    WormDotsIndicator DI;
    int currentPage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Introduction_Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        VP = findViewById(R.id.introductionVP);
        Next = findViewById(R.id.Next);
        Previous = findViewById(R.id.Previous);

        IntroductionData id = new IntroductionData();
        IntroductionViewPagerAdapter adapter = new IntroductionViewPagerAdapter(this, id.titles, id.subTitles, id.imageResIds);
        VP.setAdapter(adapter);
        VP.setUserInputEnabled(false);

        currentPage = VP.getCurrentItem();

        Previous.setEnabled(false);
        Previous.setTextColor(getColor(R.color.NotActiveSubButtonText));

        DI = findViewById(R.id.dots_indicator);
        DI.setViewPager2(VP);
        DI.setDotsClickable(false);
        intent = new Intent(MainActivity.this, ChaptersActivity.class);





    }

    @Override
    protected void onStart() {
        super.onStart();
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentPage){
                    case 0:
                        currentPage = currentPage + 1;
                        VP.setCurrentItem(currentPage);
                        Previous.setEnabled(true);
                        Previous.setTextColor(getColor(R.color.ButtonText));
                        break;
                    case 1:
                        currentPage = currentPage + 1;
                        VP.setCurrentItem(currentPage);
                        Next.setText("Start Now!");
                        break;
                    case 2:
                        startActivity(intent);
                        break;
                }
            }
        });

        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentPage){
                    case 1:
                        currentPage = currentPage - 1;
                        VP.setCurrentItem(currentPage);
                        Previous.setEnabled(false);
                        Previous.setTextColor(getColor(R.color.NotActiveSubButtonText));
                        break;
                    case 2:
                        currentPage = currentPage - 1;
                        VP.setCurrentItem(currentPage);
                        Next.setText("Next");

                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPage", currentPage);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentPage = savedInstanceState.getInt("currentPage", 5);
        if(currentPage != 0){
            Previous.setEnabled(true);
            Previous.setTextColor(getColor(R.color.ButtonText));
        }
        if(currentPage == 2){
            Next.setText("Start Now!");
        }
    }

}