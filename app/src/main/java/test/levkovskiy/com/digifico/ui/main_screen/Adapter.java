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

public class Adapter extends RecyclerView.Adapter<Adapter.AnimalViewHolder> {
    private List<NewsModel> animals;

    Adapter() {

        this.animals = new ArrayList<>();

    }

    NewsModel getItem(int pos) {
        return animals.get(pos);
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemListBinding binding = ItemListBinding.inflate(inflater, parent, false);
        return new AnimalViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        NewsModel movie = animals.get(position);
        holder.binding.setNews(movie);

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent detailsIntent = new Intent(holder.binding.getRoot().getContext(), DetailsActivity.class);
            detailsIntent.putExtra("item", movie);
            holder.binding.getRoot().getContext().startActivity(detailsIntent);
        });

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    void addAll(List<NewsModel> animalModelWebResponse) {
        animals.addAll(animalModelWebResponse);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            animals.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        else
            Collections.sort(animals, (o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        notifyDataSetChanged();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding binding;

        AnimalViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }
    }
}
