package ssu.tholland.androidlab5.presenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ssu.tholland.androidlab5.interactor.RecipeInteractor;
import ssu.tholland.androidlab5.models.RecipeResponse;
import ssu.tholland.androidlab5.view.RecipeView;

public class RecipePresenter {

    private RecipeView view;
    private RecipeInteractor interactor;

    public RecipePresenter(RecipeView view) {
        this.view = view;
        this.interactor = new RecipeInteractor();
    }


    public void getRecipes(String ingredient) {
        interactor.getRecipes(ingredient)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<RecipeResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(RecipeResponse response) {
                    view.displayRecipeData(response.getRecipes());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }
}
