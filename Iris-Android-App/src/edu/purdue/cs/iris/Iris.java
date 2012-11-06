package edu.purdue.cs.iris;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Iris extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iris);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_iris, menu);
        return true;
    }
}
