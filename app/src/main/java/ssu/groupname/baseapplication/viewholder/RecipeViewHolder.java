package ssu.groupname.baseapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import ssu.groupname.baseapplication.R;
import ssu.groupname.baseapplication.models.RecipeModel;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private TextView itemNameTextView;
    private TextView itemRatingTextView;
    private TextView itemTimeTextView;
    Button details;

    public RecipeViewHolder(View itemView) {
        super(itemView);

        itemNameTextView = itemView.findViewById(R.id.recycler_item_name);
        itemRatingTextView = itemView.findViewById(R.id.recycler_item_rating);
        itemTimeTextView = itemView.findViewById(R.id.recycler_item_time);
        details = itemView.findViewById(R.id.recycler_item_button);

    }

    public void bindView(RecipeModel model) {
        itemNameTextView.setText(model.getRecipeName());
        itemRatingTextView.setText(String.format("Rating: %d / 5", model.getRating()));
        itemTimeTextView.setText(String.format("Prep Time: %d min", model.getTotalTimeInSeconds()/60));
    }
}
