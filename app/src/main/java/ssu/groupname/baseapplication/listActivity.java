package ssu.groupname.baseapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class listActivity extends AppCompatActivity {

    TextView lst;
    EditText ingredientid;
    EditText ingredientname;
    EditText ingredientEXP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = (TextView) findViewById(R.id.list);
        ingredientid = (EditText) findViewById(R.id.ingredientID);
        ingredientname = (EditText) findViewById(R.id.ingredientName);
        ingredientEXP = (EditText) findViewById(R.id.ingredientExp);
    }
    public void addIngredient (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        int id = Integer.parseInt(ingredientid.getText().toString());
        String name = ingredientname.getText().toString();
        String EXP = ingredientEXP.getText().toString();
        Ingredient ingredient = new Ingredient(id,name,EXP);
        dbHandler.addHandler(ingredient);
        ingredientid.setText("");
        ingredientname.setText("");
        ingredientEXP.setText("");
    }

    public void findIngredient (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Ingredient ingredient = dbHandler.findHandler(ingredientname.getText().toString());
        if (ingredient != null) {
            lst.setText(String.valueOf(ingredient.getID()) +" "+ ingredient.getIngredientName());

        } else {
            lst.setText("No Match Found");
        }
    }

    public void loadIngredients(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        ingredientid.setText("");
        ingredientname.setText("");
        ingredientEXP.setText("");
    }

    public void deleteIngredient (View view) {
        DBHandler dbHandler = new DBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(
                ingredientid.getText().toString()));
        if (result) {
            ingredientid.setText("");
            ingredientname.setText("");
            ingredientEXP.setText("");
            lst.setText("Record Deleted");
        } else
            ingredientid.setText("No Match Found");
    }

    public void updateIngredient(View view) {
        DBHandler dbHandler = new DBHandler(this, null,
                null, 1);
        boolean result = dbHandler.updateHandler(Integer.parseInt(
                ingredientid.getText().toString()), ingredientname.getText().toString(), ingredientEXP.getText().toString());
        if (result) {
            ingredientid.setText("");
            ingredientname.setText("");
            ingredientEXP.setText("");
            lst.setText("Record Updated");
        } else
            ingredientid.setText("No Match Found");
    }
}