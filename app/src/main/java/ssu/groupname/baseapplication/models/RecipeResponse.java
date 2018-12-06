package ssu.groupname.baseapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeResponse {

    @SerializedName("matches")
    private List<RecipeModel> recipes;

    public List<RecipeModel> getRecipes() {
        return recipes;
    }
}