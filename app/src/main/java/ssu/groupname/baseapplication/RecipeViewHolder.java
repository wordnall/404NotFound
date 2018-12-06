package ssu.groupname.baseapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import ssu.groupname.Models.RecipeModel;


public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private TextView itemNameTextView;
    private TextView itemRatingTextView;
    private TextView itemTimeTextView;
    private ImageView imageView;
    Button details;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.recycler_item_image);
        itemNameTextView = itemView.findViewById(R.id.recycler_item_name);
        itemRatingTextView = itemView.findViewById(R.id.recycler_item_rating);
        itemTimeTextView = itemView.findViewById(R.id.recycler_item_time);

    }

    public void bindView(RecipeModel model){
        itemNameTextView.setText(model.getRecipeName());
        Glide.with(itemView)
                .load(model.getSmallImageUrls().get(0))
                .apply(new RequestOptions().override(imageView.getWidth(),imageView.getHeight()))
                .into(imageView);
        itemRatingTextView.setText(String.format("Rating: %d / 5", model.getRating()));
        itemTimeTextView.setText(String.format("Prep Time: %d min", model.getTotalTimeInSeconds()/60));
    }
}
