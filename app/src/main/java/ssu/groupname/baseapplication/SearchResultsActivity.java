package ssu.groupname.baseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import ssu.groupname.baseapplication.viewholder.RecipeViewAdapter;

public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        RecipeViewAdapter adapter = new RecipeViewAdapter(getIntent().getParcelableArrayListExtra("RecipeModel"));
        recyclerView.setAdapter(adapter);
    }

}