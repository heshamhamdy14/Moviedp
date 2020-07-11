package com.example.moviedp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.moviedp.Adapters.MovieAdapter;
import com.example.moviedp.pojo.Result;

import java.util.List;

public class MoviesList_Act extends AppCompatActivity {
    MovieViewModel movieViewModel;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list_);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        movieViewModel=new ViewModelProvider(this).get(MovieViewModel.class);
//        movieViewModel= ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getmovies();

        recyclerView=findViewById(R.id.list_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter=new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.setListner(new MovieAdapter.OnItemClickListner() {
            @Override
            public void onitemclick(Result result) {
                Intent intent=new Intent(MoviesList_Act.this , MovieDetails.class);
                intent.putExtra("movie_image",result.getPosterPath());
                intent.putExtra("title",result.getTitle());
                intent.putExtra("overview",result.getOverview());
                startActivity(intent);
            }
        });


        movieViewModel.mutableLiveData.observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
             movieAdapter.setMovielist(results);
            }
        });


//        MovieClient.getinstance().getmovies().enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(@NonNull Call<MovieModel> call,@NonNull Response<MovieModel> response) {
//                 model=response.body();
//                 results =model.getResults();
//                movieAdapter.setMovielist(results);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<MovieModel> call,@NonNull Throwable t) {
//                t.getMessage();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(MoviesList_Act.this , Login_Act.class));
                finish();
                return true;
        }
        return false;
    }
}
