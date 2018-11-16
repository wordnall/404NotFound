package ssu.groupname.baseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {

    TextView flavorRating, spicy, sweet, salty, bitter, savory, sour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner = (Spinner) findViewById(R.id.cook_time_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CookTime, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        SeekBar spicyBar = findViewById(R.id.spicyBar);
        SeekBar sweetBar = findViewById(R.id.sweetBar);
        SeekBar saltyBar = findViewById(R.id.saltyBar);
        SeekBar bitterBar = findViewById(R.id.bitterBar);
        SeekBar savoryBar = findViewById(R.id.savoryBar);
        SeekBar sourBar = findViewById(R.id.sourBar);

        spicyBar.setOnSeekBarChangeListener(seekBarChangeListener);
        int rating = spicyBar.getProgress();
        flavorRating = findViewById(R.id.spicy);
        flavorRating.append(": " + rating);

        sweetBar.setOnSeekBarChangeListener(seekBarChangeListener);
        rating = sweetBar.getProgress();
        flavorRating = findViewById(R.id.sweet);
        flavorRating.append(": " + rating);

        saltyBar.setOnSeekBarChangeListener(seekBarChangeListener);
        rating = sweetBar.getProgress();
        flavorRating = findViewById(R.id.sweet);
        flavorRating.append(": " + rating);

        bitterBar.setOnSeekBarChangeListener(seekBarChangeListener);
        rating = sweetBar.getProgress();
        flavorRating = findViewById(R.id.sweet);
        flavorRating.append(": " + rating);

        savoryBar.setOnSeekBarChangeListener(seekBarChangeListener);
        rating = sweetBar.getProgress();
        flavorRating = findViewById(R.id.sweet);
        flavorRating.append(": " + rating);

        sourBar.setOnSeekBarChangeListener(seekBarChangeListener);
        rating = sweetBar.getProgress();
        flavorRating = findViewById(R.id.sweet);
        flavorRating.append(": " + rating);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            flavorRating.append(": " + i);
          /*  switch(seekBar.getId()) {
                case R.id.spicyBar:
                    spicy.setText("Spicy: " + i);
                    break;
                case R.id.sweetBar:
                    sweet.setText("Sweet: " + i);
                    break;
                case R.id.saltyBar:
                    salty.setText("Salty: " + i);
                    break;
                case R.id.bitterBar:
                    bitter.setText("Bitter: " + i);
                    break;
                case R.id.savoryBar:
                    savory.setText("Savory: " + i);
                    break;
                case R.id.sourBar:
                    sour.setText("Sour: " + i);
                    break;
            }*/
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


}
