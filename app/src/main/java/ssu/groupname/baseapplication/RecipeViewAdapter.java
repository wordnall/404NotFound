package ssu.groupname.baseapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.annotations.NonNull;
import ssu.groupname.Models.RecipeModel;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private List<RecipeModel> recipeCollection;
    public Context context;

    public RecipeViewAdapter(List<RecipeModel> collection) {
        recipeCollection = collection;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);
        return new RecipeViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
        RecipeModel model = recipeCollection.get(position);

        recipeViewHolder.bindView(model);
        recipeViewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchDetailActivity.class);
                intent.putExtra("details", recipeCollection.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeCollection.size();
    }
}
