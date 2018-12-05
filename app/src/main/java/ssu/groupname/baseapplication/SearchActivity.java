package ssu.groupname.baseapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ssu.groupname.Models.RecipeModel;
import ssu.groupname.network.RecipeSearchAsyncTask;

public class SearchActivity extends AppCompatActivity implements Serializable {

    private Button searchButton;
    private CheckBox vegetarianDiet, lactoDiet, ovoDiet, veganDiet, pescetarianDiet, paleoDiet;
    private EditText searchEditText;
    private RecipeSearchAsyncTask.RecipeCallbackListener recipeCallbackListener;
    private RecyclerView recyclerView;


    private TextView spicy, sweet, salty, bitter, savory, sour;
    private SeekBar spicyBar, sweetBar, saltyBar, bitterBar, savoryBar, sourBar;


    private double spicyRating, sweetRating, saltyRating, bitterRating, savoryRating, sourRating;
    private String paleoParam, lactoParam, pescetarianParam, ovoParam, veganParam, vegetarianParam;
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
        spicy.setText("Spicy: " + (int)spicyRating);

        sweetBar.setOnSeekBarChangeListener(seekBarChangeListener);
        sweetRating = sweetBar.getProgress();
        sweet.setText("Sweet: " + (int)sweetRating);

        saltyBar.setOnSeekBarChangeListener(seekBarChangeListener);
        saltyRating = sweetBar.getProgress();
        salty.setText("Salty: " + (int)saltyRating);

        bitterBar.setOnSeekBarChangeListener(seekBarChangeListener);
        bitterRating = sweetBar.getProgress();
        bitter.setText("Bitter: " + (int)bitterRating);

        savoryBar.setOnSeekBarChangeListener(seekBarChangeListener);
        savoryRating = sweetBar.getProgress();
        savory.setText("Savory: " + (int)savoryRating);

        sourBar.setOnSeekBarChangeListener(seekBarChangeListener);
        sourRating = sweetBar.getProgress();
        sour.setText("Sour: " + (int)sourRating);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (veganDiet.isChecked()) {
                    veganParam = "386^Vegan";
                }
                if(vegetarianDiet.isChecked()){
                    vegetarianParam = "387^Lacto-ovo vegetarian";
                }
                if (paleoDiet.isChecked()) {
                    paleoParam = "403^Paleo";
                }
                if (lactoDiet.isChecked()) {
                    lactoParam = "388^Lacto vegetarian";
                }
                if (ovoDiet.isChecked()) {
                    ovoParam = "389^Ovo vegetarian";
                }
                if (pescetarianDiet.isChecked()) {
                    pescetarianParam = "390^Pescetarian";
                }

                RecipeSearchAsyncTask task = new RecipeSearchAsyncTask();
                task.setListener(new RecipeSearchAsyncTask.RecipeCallbackListener() {
                    @Override
                    public void onRecipeCallback(List<RecipeModel> models) {

                        Intent recyclerViewIntent = new Intent(SearchActivity.this, RecyclerViewActivity.class);
                        /*Bundle bundle = new Bundle();

                        bundle.putParcelableArrayList("RecipeModel", (ArrayList)models);*/
                        recyclerViewIntent.putParcelableArrayListExtra("RecipeModel", (ArrayList)models);
                        startActivity(recyclerViewIntent);
                    }
                });
                task.execute(searchEditText.getText().toString(), spinner.getSelectedItem().toString(),
                        veganParam, vegetarianParam, paleoParam, lactoParam, ovoParam, pescetarianParam,
                        String.valueOf(spicyRating/100), String.valueOf(sweetRating/100), String.valueOf(saltyRating/100), String.valueOf(bitterRating/100), String.valueOf(savoryRating/100), String.valueOf(sourRating/100));
            }
        });
    }
  
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch(seekBar.getId()) {
                case R.id.spicyBar:
                    spicy.setText("Spicy: " + i);
                    spicyRating = i;
                    break;
                case R.id.sweetBar:
                    sweet.setText("Sweet: " + i);
                    sweetRating = i;
                    break;
                case R.id.saltyBar:
                    salty.setText("Salty: " + i);
                    saltyRating = i;
                    break;
                case R.id.bitterBar:
                    bitter.setText("Bitter: " + i);
                    bitterRating = i;
                    break;
                case R.id.savoryBar:
                    savory.setText("Savory: " + i);
                    savoryRating = i;
                    break;
                case R.id.sourBar:
                    sour.setText("Sour: " + i);
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
