package com.example.textpaser;

import android.content.Intent;
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
    TextView text;
    Button web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.textview);
        Button btn=(Button)findViewById(R.id.text_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new doit().execute();
            }
        });

        web=(Button)findViewById(R.id.web_button);
        web.setOnClickListener(v -> {
            Intent web = new Intent(getApplicationContext(), WebActivity2.class);
            startActivity(web);
        });

    }

    public class doit extends AsyncTask<Void, Void, Void>{

        String words;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect("https://www.auca.kg/").get();
//                Document doc = Jsoup.connect("https://iuca.kg/ru/").get();

                words=doc.text();
            }catch (Exception e){e.printStackTrace();}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            text.setText(words);
        }
    }

}