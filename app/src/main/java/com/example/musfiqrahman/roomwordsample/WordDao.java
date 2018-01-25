package com.example.musfiqrahman.roomwordsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by musfiqrahman on 2018-01-17.
 */

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("SELECT * FROM Word ORDER BY word ASC")
    LiveData<List<Word>> getAll();

    @Update
    void update(Word word);
}
