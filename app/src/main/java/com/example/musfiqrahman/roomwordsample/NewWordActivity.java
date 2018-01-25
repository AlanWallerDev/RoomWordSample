package com.example.musfiqrahman.roomwordsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewWordActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;

    public static final String EXTRA_REPLY = "com.example.musfiqrahman.roomwordsample.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        editText = (EditText) findViewById(R.id.edit_word);
        saveButton = (Button) findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reply = new Intent();
                if(TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED, reply);
                }else{
                    String word = editText.getText().toString();
                    reply.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, reply);
                }
                finish();
            }
        });


    }
}
