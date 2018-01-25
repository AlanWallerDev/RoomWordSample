package com.example.musfiqrahman.roomwordsample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by musfiqrahman on 2018-01-17.
 */

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int wid;

    private String word;


    Word(@NonNull String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWid(int id){
        this.wid = id;
    }
    public int getWid(){
        return wid;
    }
}
