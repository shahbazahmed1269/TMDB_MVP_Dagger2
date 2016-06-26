package in.shapps.tmdb_mvp_dagger2.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.shapps.tmdb_mvp_dagger2.Adapter.MovieAdapter;
import in.shapps.tmdb_mvp_dagger2.Application.MovieApplication;
import in.shapps.tmdb_mvp_dagger2.Presenter.MoviePresenter;
import in.shapps.tmdb_mvp_dagger2.R;
import in.shapps.tmdb_mvp_dagger2.Service.MovieService;
import in.shapps.tmdb_mvp_dagger2.model.Constants;
import in.shapps.tmdb_mvp_dagger2.model.MovieResponse;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements MovieViewInterface{
    @Inject
    MovieService service;
    private MoviePresenter mPresenter;
    private MovieAdapter mAdapter;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MovieApplication) getApplication()).getApiComponent().inject(MainActivity.this);
        mPresenter = new MoviePresenter(MainActivity.this);
        mPresenter.onCreate();
        ButterKnife.bind(MainActivity.this);
        setupVIew();

    }

    private void setupVIew() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.hasFixedSize();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new MovieAdapter(getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchMovies();
        mDialog=new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading list");
        mDialog.setMessage("Please Wait");
        mDialog.show();
    }

    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Download Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovies(MovieResponse movieResponse) {
        mAdapter.addMovies(movieResponse.getResults());

    }

    @Override
    public Observable<MovieResponse> getMovies() {
        return service.getTopRatedMovies(Constants.API_KEY);
    }
}
