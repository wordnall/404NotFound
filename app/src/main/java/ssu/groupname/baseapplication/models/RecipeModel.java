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
    private double piquant = 0;
    private double meaty = 0;
    private double bitter = 0;
    private double sweet = 0;
    private double sour = 0;
    private double salty = 0;

    public List<String> getIngredients() { return ingredients; }
    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }
    public String getRecipeName() {
        return recipeName;
    }
    public int getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }
    public double getFlavorPiquant() { return piquant; }
    public double getFlavorMeaty() { return meaty; }
    public double getFlavorBitter() { return bitter; }
    public double getFlavorSweet() { return sweet; }
    public double getFlavorSour() { return sour; }
    public double getFlavorSalty() { return salty; }
    public int getRating() {
        return rating;
    }
    public boolean FlavorTest() {
        if (flavors == null)
            return false;
        else
            return true;
    }

    private class FlavorProfile implements Serializable {
        public double piquant;
        public double meaty;
        public double bitter;
        public double sweet;
        public double sour;
        public double salty;
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
        dest.writeDouble(piquant);
        dest.writeDouble(meaty);
        dest.writeDouble(bitter);
        dest.writeDouble(sweet);
        dest.writeDouble(sour);
        dest.writeDouble(salty);
        dest.writeList(ingredients);
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