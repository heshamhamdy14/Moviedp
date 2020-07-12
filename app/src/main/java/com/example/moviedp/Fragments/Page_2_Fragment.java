package com.example.moviedp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedp.Adapters.MovieAdapter;
import com.example.moviedp.MovieDetails;
import com.example.moviedp.MovieViewModel;
import com.example.moviedp.R;
import com.example.moviedp.pojo.Result;

import java.util.List;

public class Page_2_Fragment extends Fragment {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page_1_,container ,false);
        movieViewModel=new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getmoviesforpage2();
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter=new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        movieViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                movieAdapter.setMovielist(results);
            }
        });
        movieAdapter.setListner(new MovieAdapter.OnItemClickListner() {
            @Override
            public void onitemclick(Result result) {
                Intent intent=new Intent(getContext() , MovieDetails.class);
                intent.putExtra("movie_image",result.getPosterPath());
                intent.putExtra("title",result.getTitle());
                intent.putExtra("overview",result.getOverview());
                startActivity(intent);
            }
        });
        return view;
    }
}
