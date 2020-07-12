package com.example.moviedp.Api;

import com.example.moviedp.pojo.MovieModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private String Base_Url="http://api.themoviedb.org/3/";
    private MovieInterface movieInterface;
    private static MovieClient instance;

    public MovieClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieInterface=retrofit.create(MovieInterface.class);
    }

    public static MovieClient getinstance(){
        if (instance==null){
            instance=new MovieClient();
        }
        return instance;
    }

    public Call<MovieModel> getmoviesforpage1(){
       // return movieInterface.getmovies("popular","09d250566c89bfaf1a7a9f6ed6bb02cb","en-US",2);
        return movieInterface.getmovies("popular","09d250566c89bfaf1a7a9f6ed6bb02cb","en-US",1);

    }
    public Call<MovieModel> getmoviesforpage2(){
        // return movieInterface.getmovies("popular","09d250566c89bfaf1a7a9f6ed6bb02cb","en-US",2);
        return movieInterface.getmovies("popular","09d250566c89bfaf1a7a9f6ed6bb02cb","en-US",2);

    }

}
