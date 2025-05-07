package com.example.backlogtracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backlogtracker.R;
import com.example.backlogtracker.database.Repository;
import com.example.backlogtracker.entities.Game;
import com.example.backlogtracker.entities.Playstation;
import com.example.backlogtracker.entities.Xbox;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private Repository repository;
    private List<Game> mGames;
    private final Context context;
    private final LayoutInflater mInflater;
    public GameAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    class GameViewHolder extends RecyclerView.ViewHolder {
        private final TextView gameTitle;
        private final TextView gameGenre;
        private final TextView gameTrophies;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.title);
            gameGenre = itemView.findViewById(R.id.genre);
            gameTrophies = itemView.findViewById(R.id.trophies);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Game current = mGames.get(position);
                    Intent intent = new Intent(context, GameDetails.class);
                    intent.putExtra("id", current.getGameID());
                    intent.putExtra("name", current.getGameName());
                    intent.putExtra("genre", current.getGenre());
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View itemView = mInflater.inflate(R.layout.game_list_item, parent, false);
        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        if(mGames != null) {
            Game current = mGames.get(position);
            int trophies = 0;
            for (Playstation p : repository.getAllPlaystation()) {
                if (p.getGameID() == current.getGameID()) {
                    trophies = p.getTrophies();
                }
            }
            for (Xbox x : repository.getAllXbox()) {
                if (x.getGameID() == current.getGameID()) {
                    trophies = x.getAchievements();
                }
            }

            String name = current.getGameName();
            String genre = current.getGenre();
            holder.gameTitle.setText(name);
            holder.gameGenre.setText(genre);
            holder.gameTrophies.setText(trophies);
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
