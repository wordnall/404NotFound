package ssu.groupname.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeSearchAsyncTask extends AsyncTask<String, Void, List<RecipeModel>> {

    private final String baseApiUrl = "http://api.yummly.com/v1/api/recipes";
    private final String apiKey = "ec3e34e0bb6801670dbd3dbd02ce7608";
    private final String appId = "4911b643";

    private RecipeCallbackListener listener;


    @Override
    protected List<RecipeModel> doInBackground(String... params) {
        String searchParam = params[0];
        String flavors = params[1];
        String diets = params[2];
        String time = params[3];

        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(baseApiUrl).newBuilder()
                .addQueryParameter("_app_key", apiKey)
                .addQueryParameter("_app_id", appId)
                .addQueryParameter("maxResult", "30")
                .addQueryParameter("q", searchParam)
                .addQueryParameter("flavor", flavors)
                .addQueryParameter("")
                .build();
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
