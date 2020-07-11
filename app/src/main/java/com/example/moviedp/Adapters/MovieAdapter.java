package com.example.moviedp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedp.R;
import com.example.moviedp.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
     private List<Result>movielist=new ArrayList<>() ;
     OnItemClickListner listner;
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.title.setText(movielist.get(position).getTitle());
        holder.releasedate.setText(movielist.get(position).getReleaseDate());
        Picasso.get().load(movielist.get(position).getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {

        return movielist.size();
    }

    public void setMovielist(List<Result> movielist) {
        this.movielist = movielist;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView title , releasedate;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            releasedate=itemView.findViewById(R.id.releasedate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null){
                        int position=getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listner.onitemclick(movielist.get(position));
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListner{
        void onitemclick(Result result);
    }
    public void setListner(OnItemClickListner listner){
        this.listner=listner;
    }
}
