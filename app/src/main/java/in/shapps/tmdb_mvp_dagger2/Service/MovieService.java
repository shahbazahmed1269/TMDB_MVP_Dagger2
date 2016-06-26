package in.shapps.tmdb_mvp_dagger2.Service;

/**
 * Created by James on 6/26/2016.
 */
import java.util.List;

import in.shapps.tmdb_mvp_dagger2.model.Movie;
import in.shapps.tmdb_mvp_dagger2.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface MovieService {
    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}