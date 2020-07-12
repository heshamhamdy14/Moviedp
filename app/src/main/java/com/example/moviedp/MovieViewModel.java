package com.example.moviedp;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedp.Api.MovieClient;
import com.example.moviedp.pojo.MovieModel;
import com.example.moviedp.pojo.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    public MutableLiveData <List<Result>> mutableLiveData=new MutableLiveData<>();
   // MutableLiveData<String>onfailure=new MutableLiveData<>();

    public void getmoviesforpage1(){
        MovieClient.getinstance().getmoviesforpage1().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {

                    MovieModel model=response.body();
                    List<Result> results =model.getResults();
                    mutableLiveData.setValue(results);
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                call.cancel();
            }
        });
    }

    public void getmoviesforpage2(){
        MovieClient.getinstance().getmoviesforpage2().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {

                MovieModel model=response.body();
                List<Result> results =model.getResults();
                mutableLiveData.setValue(results);
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                call.cancel();
            }
        });
    }
}
