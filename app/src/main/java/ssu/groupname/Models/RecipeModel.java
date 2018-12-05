package ssu.groupname.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class RecipeModel implements Parcelable {
    // class member variables
    //make getters and setters
    private String recipeName;
    private int rating;
    private int totalTimeInSeconds;
    private String recipeImageUrl;
    private List<String> smallImageUrls;

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getRating() {
        return rating;
    }

    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }


    protected RecipeModel(Parcel in) {
        recipeName = in.readString();
        rating = in.readInt();
        totalTimeInSeconds = in.readInt();
        recipeImageUrl = in.readString();
        if (in.readByte() == 0x01) {
            smallImageUrls = new ArrayList<String>();
            in.readList(smallImageUrls, String.class.getClassLoader());
        } else {
            smallImageUrls = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeName);
        dest.writeInt(rating);
        dest.writeInt(totalTimeInSeconds);
        dest.writeString(recipeImageUrl);
        if (smallImageUrls == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(smallImageUrls);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeModel> CREATOR = new Parcelable.Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };
}
