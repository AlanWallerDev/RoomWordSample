package com.example.musfiqrahman.roomwordsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel wordViewModel;


    private final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;


    private WordRecyclerViewAdapter mWordRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);

        mWordRecyclerViewAdapter = new WordRecyclerViewAdapter(this);
        rv.setAdapter(mWordRecyclerViewAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // TODO (MA1) Instantiate wordViewModel from the WordViewModel class using ViewModelProviders.of(this).get()
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        // TODO (MA2) attach observer for the LiveData object for allWords returned from the WordViewModel
        wordViewModel.getmAllwords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                mWordRecyclerViewAdapter.setWords(words);
            }
        });
        // TODO (MA3) Then override the onChange() method and update the recylerView with the word list.


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO (MA4) Create an Intent for the NewWordActivity
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);

                // TODO (MA5) start the intent with result
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
           // TODO (MA6) instantiate a Word object from the data received back from the Intent
        Bundle b = data.getExtras();
        Word word = new Word(data.getExtras().getString("com.example.musfiqrahman.roomwordsample.REPLY"));
        Log.d("word", word.getWord() + "");
            // TODO (MA7)insert the word object to the WordModelView.
            wordViewModel.insert(word);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Use this method if you want to show data in the log file
    public void logWords(List<Word> l){
        for(Word w: l)
            Log.d("WordApp", w.getWord());
    }
}
