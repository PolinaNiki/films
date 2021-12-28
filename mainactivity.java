package com.example.filmcomplicated;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


class Film{
    String name;
    String year;
    String genre;
    String duration;
    String director;
}

class Films{
    ArrayList<Film> films;
}


public class MainActivity extends AppCompatActivity {
    TextView filmTV;
    TextView year;
    TextView genre;
    TextView duration;
    TextView director;


    ArrayList<Film> films;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        films =loadFilms();
        filmTV = findViewById(R.id.filmname);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        duration = findViewById(R.id.duration);
        director = findViewById(R.id.director);
    }
    public ArrayList<Film> loadFilms() {
        InputStream stream = null;
        try {
            stream = getAssets().open("films.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(stream);
        Gson gson = new Gson();
        Films f = gson.fromJson(reader, Films.class);
        return f.films;
    }


    public void onClick(View v){

        if (films.size() !=0) {
            int randomindex = (int) (Math.random() * films.size());
            filmTV.setText(films.get(randomindex).name);
            year.setText(films.get(randomindex).year);
            genre.setText(films.get(randomindex).genre);
            duration.setText(films.get(randomindex).duration);
            director.setText(films.get(randomindex).director);
            films.remove(randomindex);
        }
        else filmTV.setText("Run out!");
    }


}