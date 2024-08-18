package com.example.fluenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

public class ChaptersActivity extends AppCompatActivity {
    private Button prevButton, nextButton;
    private ImageButton drawer_btn;

    private DrawerLayout drawerLayout;
    private NavigationView endNavigationView;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        EdgeToEdge.enable(this);

        // Setup Drawer
        drawerLayout = findViewById(R.id.Drawer_Layout);
        // Setup ViewPager2
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ChaptersViewPagerAdapter(this));
        viewPager.setUserInputEnabled(false);
        // Setup Buttons
        prevButton = findViewById(R.id.Previous_Chapters);
        nextButton = findViewById(R.id.Next_Chapters);
        drawer_btn = findViewById(R.id.Chapters_Drawer_Button);
        // Handle right drawer (end)
        endNavigationView = findViewById(R.id.End_Navigation_View);




    }

    @Override
    protected void onStart() {
        super.onStart();

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem > 0) {
                    viewPager.setCurrentItem(currentItem - 1);
                    if(currentItem == viewPager.getAdapter().getItemCount() - 1){
                        nextButton.setText("Next");
                    }
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem(); // 0 2 3  4
                if (currentItem < viewPager.getAdapter().getItemCount() - 1) { //
                    viewPager.setCurrentItem(currentItem + 1);
                    if(currentItem == viewPager.getAdapter().getItemCount() - 2){
                        nextButton.setText("Finish!");
                    }
                }
                if(currentItem == viewPager.getAdapter().getItemCount() - 1){
                    Intent intent = new Intent(ChaptersActivity.this, CongratulationActivity.class);
                    startActivity(intent);
                }
            }
        });

        drawer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        endNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.introduction){startActivity(new Intent(getBaseContext(), MainActivity.class));}
                else if(item.getItemId() == R.id.chapters){startActivity(new Intent(getBaseContext(), ChaptersActivity.class));}
                else if(item.getItemId() == R.id.congratulations){startActivity(new Intent(getBaseContext(), CongratulationActivity.class));}
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }


}