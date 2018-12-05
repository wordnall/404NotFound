package ssu.groupname.baseapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ssu.groupname.Models.RecipeModel;


public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private TextView itemNameTextView;
    private TextView itemRatingTextView;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        itemNameTextView = itemView.findViewById(R.id.recycler_item_name);
        itemRatingTextView = itemView.findViewById(R.id.recycler_item_rating);
    }

    public void bindView(RecipeModel model){
        itemNameTextView.setText(model.getRecipeName());

        itemRatingTextView.setText(String.format("Rating: %d / 5", model.getRating()));
    }
}
