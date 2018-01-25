package com.example.musfiqrahman.roomwordsample;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by musfiqrahman on 2018-01-17.
 */
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase{

    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word-database")
                        //.addCallback(sRoomDBCallback)
                        .build();
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDBCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final WordDao mDao;
        PopulateDbAsync(WordRoomDatabase db){
            mDao = db.wordDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.insert(new Word("Hello"));
            mDao.insert(new Word("World"));
            return null;
        }
    }







}
