package in.shapps.tmdb_mvp_dagger2.ui;

import java.util.List;

import in.shapps.tmdb_mvp_dagger2.model.MovieResponse;
import rx.Observable;

/**
 * Created by James on 6/27/2016.
 */
public interface MovieViewInterface {
    void onCompleted();

    void onError(String message);

    void onMovies(MovieResponse movieResponse);

    Observable<MovieResponse> getMovies();
}
