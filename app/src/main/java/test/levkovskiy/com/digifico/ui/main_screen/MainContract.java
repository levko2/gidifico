package test.levkovskiy.com.digifico.ui.main_screen;

import android.content.Context;

import java.util.List;

import io.reactivex.disposables.Disposable;
import test.levkovskiy.com.digifico.net.model.NewsModel;

public interface MainContract {
    interface View {
        void startProgress();

        void collectData(List<NewsModel> newsList);

        void stopProgress();

    }

    interface Present {


        Disposable getLocalData(Context context);


        Disposable getServerData(Context context);
    }
}