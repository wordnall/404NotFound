package ssu.groupname.baseapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ssu.groupname.Models.RecipeModel;

public class SearchDetailActivity extends AppCompatActivity {


    private RecipeModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        data = (RecipeModel)getIntent().getSerializableExtra("details");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        TextView name = findViewById(R.id.Name);
        name.setText(data.getRecipeName());
        TextView time = findViewById(R.id.Time);
        time.setText(String.format("Cook Time: %d Minute(s)", data.getTotalTimeInSeconds()/60));
        TextView rating = findViewById(R.id.Rating);
        rating.setText(String.format("Rating: %d / 5", data.getRating()));
        TextView flavorProfile = findViewById(R.id.Flavor_Profile);
        if (!data.FlavorTest())
            flavorProfile.append("No flavor profile avalible");
        else {
            flavorProfile.append(String.format("Piquant: %f%%\n", data.getFlavorPiquant() * 100));
            flavorProfile.append(String.format("Sweet: %f%%\n", data.getFlavorSweet() * 100));
            flavorProfile.append(String.format("Salty: %f%%\n", data.getFlavorSalty() * 100));
            flavorProfile.append(String.format("Sour: %f%%\n", data.getFlavorSour() * 100));
            flavorProfile.append(String.format("Bitter: %f%%\n", data.getFlavorBitter() * 100));
            flavorProfile.append(String.format("Meaty: %f%%", data.getFlavorMeaty() * 100));
        }
        TextView ingredientList = findViewById(R.id.Ingredient_List);
        for (int i = 0; i < data.getIngredients().size(); i++) {
            ingredientList.append(String.format("%s\n", data.getIngredients().get(i)));
        }
    }

}

