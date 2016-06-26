package in.shapps.tmdb_mvp_dagger2.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by James on 6/26/2016.
 */
@Component(modules=NetworkModule.class)
@Singleton
public interface NetworkComponent {
    Retrofit retrofit();
}
