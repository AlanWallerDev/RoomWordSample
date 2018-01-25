package com.example.musfiqrahman.roomwordsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by musfiqrahman on 2018-01-18.
 */

public class WordRecyclerViewAdapter extends RecyclerView.Adapter<WordRecyclerViewAdapter.WordViewHolder> {



    class WordViewHolder extends RecyclerView.ViewHolder{
        private final TextView wordItemView;

        private WordViewHolder(View itemView){
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.textView);
        }


    }

    private final LayoutInflater mInflater;
    private List<Word> wordList; // Create cached copy of words

    WordRecyclerViewAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    void setWords(List<Word> list){
        wordList = list;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if(wordList != null){
            Word current = wordList.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            holder.wordItemView.setText("No Word");
        }

    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflate the recycler_item_view layout
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        if(wordList != null)
            return wordList.size();
        return 0;
    }
}
