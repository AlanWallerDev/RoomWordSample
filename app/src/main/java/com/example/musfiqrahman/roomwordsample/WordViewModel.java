package com.example.musfiqrahman.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by musfiqrahman on 2018-01-17.
 */

public class WordViewModel extends AndroidViewModel {
    // Reference for the Word Repository object
    private WordRepository mRepository;

    // Reference for the Word List LiveData
    private LiveData<List<Word>> mAllword;

    /**
     * Constructor.
     * @param appliation use Application, not the context.
     */
    public WordViewModel(Application appliation){
        super(appliation);

        // TODO (VM1) instantiate Word Repository
        mRepository = new WordRepository(appliation);

        // TODO (VM2) get the Word List LiveData from the word repository and initialize the mAllword
        mAllword = mRepository.getAllWords();

    }

    /**
     *
     * @return
     */
    public LiveData<List<Word>> getmAllwords(){
        // TODO (VM3) Return the reference of the mAllword LiveData
        return mAllword;
    }

    public void insert(Word word){
        // TODO (VM4) in this case pass the request to the Word Repository
        mRepository.insertWord(word);

    }
}
