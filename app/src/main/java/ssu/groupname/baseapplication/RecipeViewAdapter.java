package ssu.groupname.baseapplication;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import ssu.groupname.Models.RecipeModel;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private List<RecipeModel> recipeCollection;

    public RecipeViewAdapter(List<RecipeModel> collection) {
        recipeCollection = collection;
    }

}
