package ssu.groupname.baseapplication;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ssu.groupname.baseapplication.models.RecipeModel;

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
            flavorProfile.append(String.format("Piquant: %f%%\n", recipe.getFlavorPiquant() * 100));
            flavorProfile.append(String.format("Sweet: %f%%\n", recipe.getFlavorSweet() * 100));
            flavorProfile.append(String.format("Salty: %f%%\n", recipe.getFlavorSalty() * 100));
            flavorProfile.append(String.format("Sour: %f%%\n", recipe.getFlavorSour() * 100));
            flavorProfile.append(String.format("Bitter: %f%%\n", recipe.getFlavorBitter() * 100));
            flavorProfile.append(String.format("Meaty: %f%%", recipe.getFlavorMeaty() * 100));
        }
        TextView ingredientList = findViewById(R.id.Ingredient_List);
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            ingredientList.append(String.format("%s\n", recipe.getIngredients().get(i)));
        }
    }

}
