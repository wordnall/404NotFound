package ssu.tholland.androidlab5.interactor;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ssu.tholland.androidlab5.models.RecipeResponse;
import ssu.tholland.androidlab5.network.RecipeApiAdapter;

public class RecipeInteractor {

    public Observable<RecipeResponse> getRecipes(String ingredient) {
        return RecipeApiAdapter.create().getRecipes(ingredient)
            .subscribeOn(Schedulers.io())
            .map(new Function<RecipeResponse, RecipeResponse>() {
                @Override
                public RecipeResponse apply(RecipeResponse response) throws Exception {
                    // post processing occurs here, while still on background thread
                    return response;
                }
            });
    }
}
