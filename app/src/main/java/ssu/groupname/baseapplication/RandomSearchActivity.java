package ssu.groupname.baseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ssu.groupname.Models.RecipeModel;
import ssu.groupname.network.RecipeSearchAsyncTask;

public class RandomSearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        ArrayList<String> dbIngredients = dbHandler.allNames();
        Random rand = new Random();
        String searchParam;
        if (dbIngredients == null)
            searchParam = "Apple";
        else if (dbIngredients.size() == 2)
            searchParam = dbIngredients.get(0);
        else
            searchParam = dbIngredients.get(rand.nextInt(dbIngredients.size()));
        RecipeSearchAsyncTask task = new RecipeSearchAsyncTask();
        task.setListener(new RecipeSearchAsyncTask.RecipeCallbackListener() {
            @Override
            public void onRecipeCallback(List<RecipeModel> models) {

                Intent recyclerViewIntent = new Intent(RandomSearchActivity.this, SearchDetailActivity.class);

                recyclerViewIntent.putExtra("details", (RecipeModel)models.get(0));
                startActivity(recyclerViewIntent);
            }
        });
        task.execute(searchParam, "None", "", "", "", "", "", "", "1", "1", "1", "1", "1", "1", "", "");
    }
}
