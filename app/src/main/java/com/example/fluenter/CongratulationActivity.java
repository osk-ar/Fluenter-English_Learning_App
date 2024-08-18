package com.example.fluenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import carbon.widget.TextView;

public class CongratulationActivity extends AppCompatActivity {

    Button share_btn;
    TextView replay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_congratulation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        share_btn = findViewById(R.id.Share_Btn);
        replay = findViewById(R.id.Replay_Tv);

    }

    @Override
    protected void onStart() {
        super.onStart();
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, I just finished Fluenter's english course!");
                startActivity(Intent.createChooser(shareIntent,"Share text via"));
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replayIntent = new Intent(CongratulationActivity.this, ChaptersActivity.class);
                startActivity(replayIntent);

            }
        });
    }
}