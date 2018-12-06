package ssu.groupname.baseapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel implements Parcelable {

    private List<String> ingredients;
    private List<String> smallImageUrls;
    private String recipeName;
    private int totalTimeInSeconds;
    private FlavorProfile flavors;
    private int rating;
    private String recipeImageUrl;
    private Boolean hasFlavor;

    public boolean FlavorTest() {
        if (flavors == null)
            hasFlavor = false;
        else
            hasFlavor = true;
        return hasFlavor;
    }
    public List<String> getIngredients() { return ingredients; }
    public List<String> getSmallImageUrls() { return smallImageUrls; }
    public String getRecipeName() { return recipeName; }
    public int getTotalTimeInSeconds() { return totalTimeInSeconds; }
    public double getFlavorPiquant() {
        if (FlavorTest())
            return flavors.piquant;
        else
            return 0;
    }
    public double getFlavorMeaty() {
        if (FlavorTest())
            return flavors.meaty;
        else
            return 0;
    }
    public double getFlavorBitter() {
        if (FlavorTest())
            return flavors.bitter;
        else
            return 0;
    }
    public double getFlavorSweet() {
        if (FlavorTest())
            return flavors.sweet;
        else
            return 0;
    }
    public double getFlavorSour() {
        if (FlavorTest())
            return flavors.sour;
        else
            return 0;
    }
    public double getFlavorSalty() {
        if (FlavorTest())
            return flavors.salty;
        else
            return 0;
    }
    public int getRating() { return rating; }

    private class FlavorProfile implements Serializable {
        public double piquant;
        public double meaty;
        public double bitter;
        public double sweet;
        public double sour;
        public double salty;
    }

        protected RecipeModel(Parcel in) {
            if (in.readByte() == 0x01) {
                ingredients = new ArrayList<String>();
                in.readList(ingredients, String.class.getClassLoader());
            } else {
                ingredients = null;
            }
            if (in.readByte() == 0x01) {
                smallImageUrls = new ArrayList<String>();
                in.readList(smallImageUrls, String.class.getClassLoader());
            } else {
                smallImageUrls = null;
            }
            recipeName = in.readString();
            totalTimeInSeconds = in.readInt();
            flavors = (FlavorProfile) in.readValue(FlavorProfile.class.getClassLoader());
            rating = in.readInt();
            recipeImageUrl = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (ingredients == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeList(ingredients);
            }
            if (smallImageUrls == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeList(smallImageUrls);
            }
            dest.writeString(recipeName);
            dest.writeInt(totalTimeInSeconds);
            dest.writeValue(flavors);
            dest.writeInt(rating);
            dest.writeString(recipeImageUrl);
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