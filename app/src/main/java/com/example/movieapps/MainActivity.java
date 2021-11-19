package com.example.movieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToListMovies(View view) {
        Intent i = new Intent(MainActivity.this, ListMoviesActivity.class);
        MainActivity.this.startActivity(i);
    }

    public void moveToAboutMe(View view) {
        Intent i = new Intent(MainActivity.this, AboutMeActivity.class);
        MainActivity.this.startActivity(i);
    }
}