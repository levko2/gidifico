package test.levkovskiy.com.digifico.ui.main_screen;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import test.levkovskiy.com.digifico.databinding.ItemListBinding;
import test.levkovskiy.com.digifico.net.model.NewsModel;
import test.levkovskiy.com.digifico.ui.details.DetailsActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.NewsViewHolder> {
    private List<NewsModel> news;

    Adapter() {

        this.news = new ArrayList<>();

    }

    NewsModel getItem(int pos) {
        return news.get(pos);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemListBinding binding = ItemListBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsModel movie = news.get(position);
        holder.binding.setNews(movie);

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent detailsIntent = new Intent(holder.binding.getRoot().getContext(), DetailsActivity.class);
            detailsIntent.putExtra("item", movie);
            holder.binding.getRoot().getContext().startActivity(detailsIntent);
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    void addAllAndSort(List<NewsModel> newsModels) {
        for (NewsModel model : newsModels) {
            if (!news.contains(model))
                news.add(model);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            news.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        else
            Collections.sort(news, (o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        notifyDataSetChanged();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding binding;

        NewsViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }
    }
}
