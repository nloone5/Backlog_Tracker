package com.example.backlogtracker.UI;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backlogtracker.R;
import com.example.backlogtracker.dao.PlaystationDAO;
import com.example.backlogtracker.database.Repository;
import com.example.backlogtracker.entities.Game;
import com.example.backlogtracker.entities.Playstation;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Game> mGames;
    private final Context context;
    private final LayoutInflater mInflater;

    public SearchAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private final TextView searchTitle;
        private final TextView searchGenre;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchTitle = itemView.findViewById(R.id.searchGame);
            searchGenre = itemView.findViewById(R.id.searchGenre);
        }
    }
    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.search_list_item, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        if(mGames != null) {
            Game current = mGames.get(position);
            String title = current.getGameName();
            String genre = current.getGenre();
            holder.searchTitle.setText(title);
            holder.searchGenre.setText(genre);
        }
    }

    @Override
    public int getItemCount() {
        if(mGames != null) {
            return mGames.size();
        } else {
            return 0;
        }
    }

    public void setGames(List<Game> games) {
        mGames = games;
        notifyDataSetChanged();
    }
}
