package com.example.fluenter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import carbon.widget.ImageView;

public class ChaptersRecyclerAdapter extends RecyclerView.Adapter<ChaptersRecyclerAdapter.ViewHolder> {

    private List<String> items;
    private Context context;
    private MediaPlayer mediaPlayer;

    public ChaptersRecyclerAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
        this.mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(items.get(0) == "xx"){
            view = LayoutInflater.from(context).inflate(R.layout.chapters_image_brick, parent, false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.chapters_text_brick, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(items.get(0) == "xx"){
            try {
                InputStream inputStream = context.getAssets().open("family_images/" + position + ".png");
                holder.imageView.setImageDrawable(Drawable.createFromStream(inputStream, null));
            } catch (IOException e) {
                e.printStackTrace();
            }
            holder.imageView.setOnClickListener(v -> playSound(position));
        }
        else {
            holder.textView.setText(items.get(position));
            if (items.get(0) == "-16777216") {
                holder.textView.setText("");
                holder.itemView.setBackgroundColor(Integer.parseInt(items.get(position)));
            }

            holder.textView.setOnClickListener(v -> playSound(position));
        }
    }

    private void playSound(int position) {
        String fileName;
        switch (items.get(0)) {
            case "A":
                fileName = "alphabet_sounds/" + (char) (position + 97) + ".mp3";
                break;
            case "-16777216":
                fileName = "colors_sounds/" + position + ".mp3";
                break;
            case "xx":
                fileName = "family_sounds/" + position + ".mp3";
                break;

            default:
                fileName = "numbers_sounds/en_num_" + position + ".mp3";

        }
        try {
            AssetFileDescriptor tafd = context.getAssets().openFd(fileName);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(tafd.getFileDescriptor(), tafd.getStartOffset(), tafd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error:" + e, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }
}
