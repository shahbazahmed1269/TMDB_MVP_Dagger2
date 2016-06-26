package in.shapps.tmdb_mvp_dagger2.dependencies;

import dagger.Component;
import in.shapps.tmdb_mvp_dagger2.ui.MainActivity;

/**
 * Created by James on 6/26/2016.
 */
@CustomScope
@Component(modules=ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity activity);
}
