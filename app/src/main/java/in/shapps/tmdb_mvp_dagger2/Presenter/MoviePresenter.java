package in.shapps.tmdb_mvp_dagger2.Presenter;

import java.util.List;

import in.shapps.tmdb_mvp_dagger2.model.MovieResponse;
import in.shapps.tmdb_mvp_dagger2.ui.MovieViewInterface;
import rx.Observable;
import rx.Observer;

/**
 * Created by James on 6/27/2016.
 */
public class MoviePresenter extends BasePresenter implements Observer<MovieResponse> {
    private MovieViewInterface mViewInterface;

    public MoviePresenter(MovieViewInterface viewInterface) {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(MovieResponse movieResponse) {
        mViewInterface.onMovies(movieResponse);
    }
    public void fetchMovies(){
        unSubscribeAll();
        subscribe(mViewInterface.getMovies(),MoviePresenter.this);
    }
}
