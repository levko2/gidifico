package test.levkovskiy.com.digifico.ui.main_screen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import test.levkovskiy.com.digifico.R;
import test.levkovskiy.com.digifico.databinding.ActivityMainBinding;
import test.levkovskiy.com.digifico.net.model.NewsModel;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    Adapter adapter;
    ActivityMainBinding binding;
    private MainContract.Present mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new Adapter();
        binding.rvNews.setAdapter(adapter);
        mPresenter.getLocalData(this);
        mPresenter.getServerData(this);


    }

    @Override
    public void startProgress() {
        binding.setIsVisible(true);

    }

    @Override
    public void collectData(List<NewsModel> newsList) {
        adapter.addAllAndSort(newsList);


    }

    @Override
    public void stopProgress() {
        binding.setIsVisible(false);

    }
}
