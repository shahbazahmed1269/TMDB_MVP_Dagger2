package in.shapps.tmdb_mvp_dagger2.Application;

import android.app.Application;

import in.shapps.tmdb_mvp_dagger2.dependencies.ApiComponent;
import in.shapps.tmdb_mvp_dagger2.dependencies.DaggerApiComponent;
import in.shapps.tmdb_mvp_dagger2.dependencies.DaggerNetworkComponent;
import in.shapps.tmdb_mvp_dagger2.dependencies.NetworkComponent;
import in.shapps.tmdb_mvp_dagger2.dependencies.NetworkModule;
import in.shapps.tmdb_mvp_dagger2.model.Constants;

/**
 * Created by James on 6/26/2016.
 */
public class MovieApplication extends Application {

    private  ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
  }
    private void resolveDependency(){
         mApiComponent= DaggerApiComponent.builder().networkComponent(getNetworkComponent()).build();
    }

    private NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
