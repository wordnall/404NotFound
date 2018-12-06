package ssu.groupname.baseapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RandomSearchActivity extends AppCompatActivity {

    DBHandler dbHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
