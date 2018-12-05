package ssu.groupname.Models;

import java.io.Serializable;
import java.util.List;

public class RecipeModel implements Serializable {
    // class member variables
    //make getters and setters
    private String recipeName;
    private int rating;
    private int totalTimeInSeconds;
    private String recipeImageUrl;
    private List<String> smallImageUrls;

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getRating() {
        return rating;
    }

    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }

}
