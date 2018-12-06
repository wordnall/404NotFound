package ssu.groupname.baseapplication;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ssu.groupname.Models.RecipeModel;

public class SearchDetailActivity extends AppCompatActivity {

    private Parcel data;
    private RecipeModel recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recipe = getIntent().getParcelableExtra("details");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        TextView name = findViewById(R.id.Name);
        name.setText(recipe.getRecipeName());
        TextView time = findViewById(R.id.Time);
        time.setText(String.format("Cook Time: %d Minute(s)", recipe.getTotalTimeInSeconds()/60));
        TextView rating = findViewById(R.id.Rating);
        rating.setText(String.format("Rating: %d / 5", recipe.getRating()));
        TextView flavorProfile = findViewById(R.id.Flavor_Profile);
        if (!recipe.FlavorTest())
            flavorProfile.append("No flavor profile avalible");
        else {
            flavorProfile.append(String.format("Piquant: %d%%\n", (int)(recipe.getFlavorPiquant() * 100)));
            flavorProfile.append(String.format("Sweet: %d%%\n", (int)(recipe.getFlavorSweet() * 100)));
            flavorProfile.append(String.format("Salty: %d%%\n", (int)(recipe.getFlavorSalty() * 100)));
            flavorProfile.append(String.format("Sour: %d%%\n", (int)(recipe.getFlavorSour() * 100)));
            flavorProfile.append(String.format("Bitter: %d%%\n", (int)(recipe.getFlavorBitter() * 100)));
            flavorProfile.append(String.format("Meaty: %d%%", (int)(recipe.getFlavorMeaty() * 100)));
        }
        TextView ingredientList = findViewById(R.id.Ingredient_List);
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            ingredientList.append(String.format("%s\n", recipe.getIngredients().get(i)));
        }
    }

}
