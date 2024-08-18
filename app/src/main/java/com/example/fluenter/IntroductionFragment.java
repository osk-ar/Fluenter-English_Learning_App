package com.example.fluenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class IntroductionFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_SUBTITLE = "subtitle";
    private static final String ARG_IMAGE = "image";

    private String title;
    private String subTitle;
    private int imageResId;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    public static IntroductionFragment newInstance(String title, String subtitle, int imageResId) {
        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_SUBTITLE, subtitle);
        args.putInt(ARG_IMAGE, imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            subTitle = getArguments().getString(ARG_SUBTITLE);
            imageResId = getArguments().getInt(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.introduction_fragment, container, false);

        // Set the text and image based on the arguments
        ImageView imageView = view.findViewById(R.id.fragmentImage);
        TextView titleView = view.findViewById(R.id.fragmentTitle);
        TextView subTitleView = view.findViewById(R.id.fragmentSubTitle);

        titleView.setText(title);
        subTitleView.setText(subTitle);
        imageView.setImageResource(imageResId);

        return view;
    }
}
