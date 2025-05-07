package com.example.backlogtracker.database;

import android.app.Application;

import com.example.backlogtracker.dao.XboxDAO;
import com.example.backlogtracker.dao.GameDAO;
import com.example.backlogtracker.dao.PlaystationDAO;
import com.example.backlogtracker.entities.Game;
import com.example.backlogtracker.entities.Playstation;
import com.example.backlogtracker.entities.Xbox;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private GameDAO mGameDAO;
    private PlaystationDAO mPlaystationDAO;
    private XboxDAO mXboxDAO;

    private List<Game> mAllGames;
    private List<Playstation> mAllPlaystation;
    private List<Xbox> mAllXbox;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        BacklogDatabaseBuilder db = BacklogDatabaseBuilder.getDatabase(application);
        mGameDAO = db.gameDAO();
        mPlaystationDAO = db.playstationDAO();
        mXboxDAO = db.xboxDAO();
    }

    public List<Game>getAllGames() {
        databaseExecutor.execute(()->{
            mAllGames = mGameDAO.getAllGames();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            throw new RuntimeException(i);
        }
        return mAllGames;
    }

    public void insert(Game game) {
        databaseExecutor.execute(()->{
            mGameDAO.insert(game);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
    public void update(Game game) {
        databaseExecutor.execute(() -> {
            mGameDAO.update(game);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
    public void delete(Game game) {
        databaseExecutor.execute(()->{
            mGameDAO.delete(game);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    public List<Playstation>getAllPlaystation() {
        databaseExecutor.execute(()->{
            mAllPlaystation = mPlaystationDAO.getAllPlaystation();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            throw new RuntimeException(i);
        }
        return mAllPlaystation;
    }
    public void insert(Playstation playstation) {
        databaseExecutor.execute(()->{
            mPlaystationDAO.insert(playstation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
    public void update(Playstation playstation) {
        databaseExecutor.execute(() -> {
            mPlaystationDAO.update(playstation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
    public void delete(Playstation playstation) {
        databaseExecutor.execute(()->{
            mPlaystationDAO.delete(playstation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    public List<Xbox> getAllXbox() {
        databaseExecutor.execute(()->{
            mAllXbox = mXboxDAO.getAllXbox();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            throw new RuntimeException(i);
        }
        return mAllXbox;
    }
    public void insert(Xbox game) {
        databaseExecutor.execute(()->{
            mXboxDAO.insert(game);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
    public void update(Xbox game) {
        databaseExecutor.execute(() -> {
            mXboxDAO.update(game);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
    public void delete(Xbox game) {
        databaseExecutor.execute(()->{
            mXboxDAO.delete(game);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
}
