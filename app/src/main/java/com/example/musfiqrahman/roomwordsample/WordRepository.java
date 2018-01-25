package com.example.musfiqrahman.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * This is the Repository class for the Words. Words are stored in an internal SQLite database,
 * this class act as a mediator between the ViewModel class and Room database.
 *
 * Created by musfiqrahman on 2018-01-17.
 */


public class WordRepository {
    // Reference to the the Word Data Access Object
    private WordDao wordDao;

    // Reference to the LiveData wordList coming from Room database
    private LiveData<List<Word>> mAllwords;

    /**
     * Constructor.
     * @param app the application for the context
     */
    WordRepository(Application app){
        // TODO (WR1) get a WordRoomDatabase instance and initialize the wordDao from the database
        wordDao = WordRoomDatabase.getDatabase(app).wordDao();
        // TODO (WR2) gel all words from the database
        mAllwords = wordDao.getAll();
    }

    public LiveData<List<Word>> getAllWords(){
        // TODO (WR2) return the local reference of LiveData returned by the WordRoomDatabase

        return mAllwords;
    }

    public void insertWord(Word word){
        // TODO (WR3) insert Word using AsyncTask class
        new InsertAsyncTask(wordDao).execute();

    }


    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao wordDao;
        InsertAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            // TODO (WR4) call the insert method from the word dao and pass the word
            wordDao.insert(words[0]);
            return null;
        }
    }
}
