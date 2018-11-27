package ssu.groupname.baseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import ssu.groupname.Models.RecipeModel;
import ssu.groupname.network.RecipeSearchAsyncTask;

public class SearchActivity extends AppCompatActivity {

    private Button searchButton;
    private CheckBox vegetarianDiet, lactoDiet, ovoDiet, veganDiet, pescetarianDiet, paleoDiet;
    private EditText searchEditText;
    private RecipeSearchAsyncTask.RecipeCallbackListener recipeCallbackListener;


    private TextView spicy, sweet, salty, bitter, savory, sour;
    private SeekBar spicyBar, sweetBar, saltyBar, bitterBar, savoryBar, sourBar;

    int spicyRating, sweetRating, saltyRating, bitterRating, savoryRating, sourRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner = (Spinner) findViewById(R.id.cook_time_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CookTime, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        searchEditText = (EditText)findViewById(R.id.search_edit_text);
        searchButton = (Button)findViewById(R.id.search_button);
        vegetarianDiet = (CheckBox)findViewById(R.id.vegetarianBox);
        veganDiet = (CheckBox)findViewById(R.id.veganBox);
        lactoDiet = (CheckBox)findViewById(R.id.lactoBox);
        ovoDiet = (CheckBox)findViewById(R.id.ovoBox);
        paleoDiet = (CheckBox)findViewById(R.id.paleoBox);
        pescetarianDiet = (CheckBox)findViewById(R.id.pescetarianBox);

        spicy = (TextView)findViewById(R.id.spicy);
        sweet = (TextView)findViewById(R.id.sweet);
        salty = (TextView)findViewById(R.id.salty);
        bitter = (TextView)findViewById(R.id.bitter);
        savory = (TextView)findViewById(R.id.savory);
        sour = (TextView)findViewById(R.id.sour);


         spicyBar = (SeekBar)findViewById(R.id.spicyBar);
         sweetBar = (SeekBar)findViewById(R.id.sweetBar);
         saltyBar = (SeekBar)findViewById(R.id.saltyBar);
         bitterBar = (SeekBar)findViewById(R.id.bitterBar);
         savoryBar = (SeekBar)findViewById(R.id.savoryBar);
         sourBar = (SeekBar)findViewById(R.id.sourBar);



        spicyBar.setOnSeekBarChangeListener(seekBarChangeListener);
        spicyRating = spicyBar.getProgress();
        spicy.append(": " + spicyRating);

        sweetBar.setOnSeekBarChangeListener(seekBarChangeListener);
        sweetRating = sweetBar.getProgress();
        sweet.append(": " + sweetRating);

        saltyBar.setOnSeekBarChangeListener(seekBarChangeListener);
        saltyRating = sweetBar.getProgress();
        salty.append(": " + saltyRating);

        bitterBar.setOnSeekBarChangeListener(seekBarChangeListener);
        bitterRating = sweetBar.getProgress();
        bitter.append(": " + bitterRating);

        savoryBar.setOnSeekBarChangeListener(seekBarChangeListener);
        savoryRating = sweetBar.getProgress();
        savory.append(": " + savoryRating);

        sourBar.setOnSeekBarChangeListener(seekBarChangeListener);
        sourRating = sweetBar.getProgress();
        sour.append(": " + sourRating);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeCallbackListener = new RecipeSearchAsyncTask.RecipeCallbackListener() {
                    @Override
                    public void onRecipeCallback(RecipeModel recipeModel) {
                        recipeName.setText(recipeModel.getRecipeName());
                    }
                };
                RecipeSearchAsyncTask task = new RecipeSearchAsyncTask();
                task.setRecipeCallbackListener(recipeCallbackListener);
                task.execute(searchEditText.getText().toString(), spinner.getSelectedItem().toString());
            }
        });
    }


    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch(seekBar.getId()) {
                case R.id.spicyBar:
                    spicy.append(": " + i);
                    spicyRating = i;
                    break;
                case R.id.sweetBar:
                    sweet.append(": " + i);
                    sweetRating = i;
                    break;
                case R.id.saltyBar:
                    salty.append(": " + i);
                    saltyRating = i;
                    break;
                case R.id.bitterBar:
                    bitter.append(": " + i);
                    bitterRating = i;
                    break;
                case R.id.savoryBar:
                    savory.append(": " + i);
                    savoryRating = i;
                    break;
                case R.id.sourBar:
                    sour.append(": " + i);
                    sourRating = i;
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


}
