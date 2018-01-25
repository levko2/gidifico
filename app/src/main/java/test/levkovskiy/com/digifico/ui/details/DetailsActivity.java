package test.levkovskiy.com.digifico.ui.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import test.levkovskiy.com.digifico.R;
import test.levkovskiy.com.digifico.databinding.ActivityDetailsBinding;
import test.levkovskiy.com.digifico.net.model.NewsModel;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        if (getIntent() != null) {
            NewsModel model = getIntent().getParcelableExtra("item");
            binding.setNews(model);
        }
    }
}
