package in.shapps.tmdb_mvp_dagger2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.shapps.tmdb_mvp_dagger2.R;
import in.shapps.tmdb_mvp_dagger2.model.Movie;
import in.shapps.tmdb_mvp_dagger2.model.MovieResponse;

/**
 * Created by James on 6/27/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private final LayoutInflater mInflator;
    private List<Movie> mMovieList;
    public MovieAdapter(LayoutInflater inflator){
        mInflator=inflator;
        mMovieList=new ArrayList<>();
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflator.inflate(R.layout.row_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.movieTitle.setText(mMovieList.get(position).getTitle()
        );
        holder.data.setText(mMovieList.get(position).getReleaseDate());
        holder.movieDescription.setText(mMovieList.get(position).getOverview());
        holder.rating.setText(mMovieList.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void addMovies(List<Movie> movieList) {
        mMovieList=movieList;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public Holder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }
}
