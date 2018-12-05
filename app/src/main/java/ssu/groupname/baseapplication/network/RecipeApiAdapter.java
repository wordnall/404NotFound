package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeApiAdapter {

    private static final String baseApiUrl = "http://api.yummly.com/v1/api/";

    private RecipeApiAdapter() { }

    public static RecipeApi create() {
        Gson gson = new GsonBuilder().create();

        return
            new Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new YummlyApiClient.Builder().build().client())
            .build()
            .create(RecipeApi.class);
    }
}
