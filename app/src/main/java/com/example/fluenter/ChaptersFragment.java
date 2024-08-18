package com.example.fluenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChaptersFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_SUBTITLE = "subtitle";
    private static final String ARG_LETTERS = "letters";

    private String title;
    private String subTitle;
    private ArrayList<String> textViewData;

    private TextView titleView, subTitleView;
    private RecyclerView recyclerView;
    private ChaptersRecyclerAdapter RV_Adapter;

    public ChaptersFragment() {
        // Required empty public constructor
    }

    public static ChaptersFragment newInstance(String title, String subtitle, ArrayList<String> CD_letters) {
        ChaptersFragment fragment = new ChaptersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_SUBTITLE, subtitle);
        args.putStringArrayList(ARG_LETTERS, CD_letters);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            subTitle = getArguments().getString(ARG_SUBTITLE);
            textViewData = getArguments().getStringArrayList(ARG_LETTERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chapters_fragment, container, false);

        // Set the text and image based on the arguments
        titleView = view.findViewById(R.id.chapter_title);
        subTitleView = view.findViewById(R.id.chapter_subtitle);

        recyclerView = view.findViewById(R.id.content_rv);
        RV_Adapter = new ChaptersRecyclerAdapter(this.getActivity().getBaseContext(),textViewData);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity().getBaseContext(), 3)); // 2 columns
        recyclerView.setAdapter(RV_Adapter);

        titleView.setText(title);
        subTitleView.setText(subTitle);

        return view;
    }
}