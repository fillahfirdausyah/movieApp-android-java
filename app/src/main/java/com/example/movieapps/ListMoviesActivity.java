package com.example.movieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.movieapps.adapter.AdapterListMovies;
import com.example.movieapps.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMoviesActivity extends AppCompatActivity {

    public  static final String TAG = "MainActivity";
    private final List<Movies> viewItems = new ArrayList<>();

    @BindView(R.id.lst_movies)
    RecyclerView lstMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);
        ButterKnife.bind(this);

        lstMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstMovies.setHasFixedSize(true);
        lstMovies.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AdapterListMovies adapterListMovies = new AdapterListMovies(this, viewItems);
        lstMovies.setAdapter(adapterListMovies);

        addItemsFromJSON();

    }


    private void addItemsFromJSON() {
        try {
            String jsonDataString = readDataFromJSON();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);

                Movies movies = new Movies();
                movies.setId(itemObj.getInt("id"));
                movies.setTitle(itemObj.getString("title"));
                movies.setReleaseDate(itemObj.getString("release_date"));
                movies.setOverview(itemObj.getString("overview"));
                movies.setPosterPath(itemObj.getString("poster_path"));

                viewItems.add(movies);
            }


        }catch (IOException | JSONException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }


    private String readDataFromJSON() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_movies);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }

}