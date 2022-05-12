package com.example.textpaser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tex1);
        Button but = (Button) findViewById(R.id.but1);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new textparse().execute();
            }
        });
    }
    public class textparse extends AsyncTask<Void, Void, Void> {
        String words;

        @Override
        protected Void doInBackground(Void...params) {
            try {
                Document doc = Jsoup.connect("https://akipress.org/").get();
                words = doc.text();
            } catch(Exception e){e.printStackTrace();}
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText(words);
        }
    }
}