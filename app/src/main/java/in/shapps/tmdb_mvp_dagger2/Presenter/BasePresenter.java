package in.shapps.tmdb_mvp_dagger2.Presenter;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by James on 6/26/2016.
 */
public abstract class BasePresenter implements Presenter{
    private CompositeSubscription mCompositeSubscription;
    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        configureSubscripton();
    }

    private CompositeSubscription configureSubscripton() {
        if(mCompositeSubscription==null || mCompositeSubscription.isUnsubscribed())
            mCompositeSubscription=new CompositeSubscription();
        return mCompositeSubscription;
    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    protected void unSubscribeAll() {
        if(mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
            mCompositeSubscription=null;
        }
    }
    protected <T> void subscribe(Observable<T> observable, Observer<T> observer){
        Subscription subscription=observable.
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.computation()).
                subscribe(observer);
        configureSubscripton().add(subscription);
    }
}