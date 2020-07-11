package com.example.moviedp.Api;

import com.example.moviedp.pojo.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {
//    @GET("discover/movie/popular?api_key=09d250566c89bfaf1a7a9f6ed6bb02cb&language=en-US")
//   Call<MovieModel> getmovies();


    @GET("movie/{movie_id}")
    Call<MovieModel> getmovies(
            @Path("movie_id")String movie_id,
            @Query("api_key")String api_key,
            @Query("language")String language,
            @Query("page") int page);
}
