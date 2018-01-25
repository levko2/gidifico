package test.levkovskiy.com.digifico.ui.main_screen;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import test.levkovskiy.com.digifico.R;
import test.levkovskiy.com.digifico.Utils;
import test.levkovskiy.com.digifico.net.model.NewsModel;

public class MainPresenter implements MainContract.Present {

    private final MainContract.View mView;
    private Gson gson;


    public MainPresenter(MainContract.View view) {
        mView = view;
        gson = new Gson();
    }


    @Override
    public Disposable getLocalData(Context context) {
        String local = Utils.JSONResourceReader(context.getResources(), R.raw.local);
        List<NewsModel> list = gson.fromJson(local, new TypeToken<List<NewsModel>>() {
        }.getType());
        mView.collectData(list);
        return null;
    }

    @Override
    public Disposable getServerData(Context context) {
        return Observable.just(Utils.isConnectingToInternet(context)).observeOn(AndroidSchedulers.mainThread()).map(aBoolean -> {
            if (aBoolean)
                mView.startProgress();
            return aBoolean;
        }).delay(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        mView.stopProgress();
                        String server = Utils.JSONResourceReader(context.getResources(), R.raw.server);
                        List<NewsModel> list = gson.fromJson(server, new TypeToken<List<NewsModel>>() {
                        }.getType());
                        mView.collectData(list);
                        Utils.writeToFile(gson.toJson(list), context);
                    }
                });
    }
}