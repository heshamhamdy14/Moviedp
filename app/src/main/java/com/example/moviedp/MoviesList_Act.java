package com.example.moviedp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.view.View;
import android.widget.Button;

import com.example.moviedp.Adapters.MovieAdapter;
import com.example.moviedp.Fragments.Page_1_Fragment;
import com.example.moviedp.Fragments.Page_2_Fragment;
import com.example.moviedp.pojo.Result;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MoviesList_Act extends AppCompatActivity {
//    MovieViewModel movieViewModel;
//    RecyclerView recyclerView;
//    MovieAdapter movieAdapter;
      Button page1 , page2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list_);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        loadfragment(new Page_1_Fragment());

        page1=findViewById(R.id.page1);
        page2=findViewById(R.id.page2);
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfragment(new Page_1_Fragment());
            }
        });
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfragment(new Page_2_Fragment());
            }
        });

//        movieViewModel=new ViewModelProvider(this).get(MovieViewModel.class);
////        movieViewModel= ViewModelProviders.of(this).get(MovieViewModel.class);
//        movieViewModel.getmovies();
//
//        recyclerView=findViewById(R.id.list_id);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        movieAdapter=new MovieAdapter();
//        recyclerView.setAdapter(movieAdapter);
//        movieAdapter.setListner(new MovieAdapter.OnItemClickListner() {
//            @Override
//            public void onitemclick(Result result) {
//                Intent intent=new Intent(MoviesList_Act.this , MovieDetails.class);
//                intent.putExtra("movie_image",result.getPosterPath());
//                intent.putExtra("title",result.getTitle());
//                intent.putExtra("overview",result.getOverview());
//                startActivity(intent);
//            }
//        });
//
//
//        movieViewModel.mutableLiveData.observe(this, new Observer<List<Result>>() {
//            @Override
//            public void onChanged(List<Result> results) {
//             movieAdapter.setMovielist(results);
//            }
//        });


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
    public void loadfragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
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
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MoviesList_Act.this , Login_Act.class));
                finish();
                return true;
        }
        return false;
    }
}
