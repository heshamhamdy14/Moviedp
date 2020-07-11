package com.example.moviedp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    ImageView image;
    TextView title , overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image=findViewById(R.id.details_image);
        title=findViewById(R.id.details_title);
        overview=findViewById(R.id.details_overview);
        Intent intent=getIntent();
        String Image= intent.getExtras().getString("movie_image");
        String Title =intent.getExtras().getString("title");
        String Overview =intent.getExtras().getString("overview");
        Picasso.get().load(Image).into(image);
        title.setText(Title);
        overview.setText(Overview);
    }
}
