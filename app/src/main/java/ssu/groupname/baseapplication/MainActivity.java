package ssu.groupname.baseapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button listButton, searchActivity, randomSearchActivity, ingredientsActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listButton =  findViewById(R.id.ingredients_page_button);// links to the ingreadents page/list page
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something
                Intent intent = new Intent(MainActivity.this, listActivity.class);

                startActivity(intent);
            }
        });

        searchActivity = findViewById(R.id.search_page_button);
        searchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

        randomSearchActivity = findViewById(R.id.random_search_button);
        randomSearchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RandomSearchActivity.class);

                startActivity(intent);
            }
        });
    }
}