package ssu.groupname.baseapplication.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ssu.groupname.Models.RecipeModel;
import ssu.groupname.baseapplication.utils.RecipeParser;

public class RecipeSearchAsyncTask extends AsyncTask<String, Void, List<RecipeModel>> {

    private final String baseApiUrl = "http://api.yummly.com/v1/api/recipes";
    private final String apiKey = "e7786cf1c7edc19107313870638da7f9";
    private final String appId = "b3fc2c4c";

    private RecipeCallbackListener listener;


    @Override
    protected List<RecipeModel> doInBackground(String... params) {
        String searchParam = params[0];
        String time = String.valueOf(Integer.parseInt(params[1]) * 60);

        String vegan = params[2];
        String vegetarian = params[3];
        String paleo = params[4];
        String lactoVegetarian = params[5];
        String ovoVegetarian = params[6];
        String pescetarian = params[7];

        String spicy = params[8];
        String sweet = params[9];
        String salty = params[10];
        String bitter = params[11];
        String savory = params[12];
        String sour = params[13];


/*
                .addQueryParameter("flavor.spicy.max", spicy)
                .addQueryParameter("flavor.sweet.max", sweet)
                .addQueryParameter("flavor.salty.max", salty)
                .addQueryParameter("flavor.bitter.max", bitter)
                .addQueryParameter("flavor.savory.max", savory)
                .addQueryParameter("flavor.sour.max", sour);
                */

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuild = HttpUrl.parse(baseApiUrl).newBuilder()
                .addQueryParameter("_app_key", apiKey)
                .addQueryParameter("_app_id", appId)
                .addQueryParameter("maxResult", "30")
                .addQueryParameter("q", searchParam)
                .addQueryParameter("maxTotalTimeInSeconds", time);

        for(int i = 2; i < 8; i++){
            if (params[i] != null) {
                urlBuild.addQueryParameter("allowedDiet[]", params[i]);
            }
        }
        HttpUrl url = urlBuild.build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response != null) {
                return RecipeParser.recipesFromJson(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<RecipeModel> recipes) {
        listener.onRecipeCallback(recipes);
    }

    public void setListener(RecipeCallbackListener listener) {
        this.listener = listener;
    }

    public interface RecipeCallbackListener {
        void onRecipeCallback(List<RecipeModel> model);
    }
}
