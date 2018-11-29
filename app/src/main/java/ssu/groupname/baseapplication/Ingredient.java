package ssu.groupname.baseapplication;

public class Ingredient {
    private int _id;//id is a number for tracking
    private String _ingredientname;//a name for the ingredient
    private String _EXPdate;//the date when it expires

    public Ingredient() {
    }
    public Ingredient(int id, String ingredientname, String EXPdate) {
        this._id = id;
        this._ingredientname = ingredientname;
        this._EXPdate = EXPdate;
    }
    public void setID(int id) {
        this._id = id;
    }
    public int getID() {
        return this._id;
    }
    public void setIngredientName(String ingredientName) {
        this._ingredientname = ingredientName;
    }
    public String getIngredientName() {
        return this._ingredientname;
    }
    public String getEXPdate (){
        return this._EXPdate;
    }
    public void setEXPdate(String EXPdate){
        this._EXPdate = EXPdate;
    }
}
