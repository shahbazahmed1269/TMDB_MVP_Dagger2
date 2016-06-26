package in.shapps.tmdb_mvp_dagger2.dependencies;

import dagger.Module;
import dagger.Provides;
import in.shapps.tmdb_mvp_dagger2.Service.MovieService;
import retrofit2.Retrofit;

/**
 * Created by James on 6/26/2016.
 */
@Module
public class ApiModule {
    @Provides
    @CustomScope
    MovieService provideMovieService(Retrofit retrofit){
        return retrofit.create(MovieService.class);
    }
}
