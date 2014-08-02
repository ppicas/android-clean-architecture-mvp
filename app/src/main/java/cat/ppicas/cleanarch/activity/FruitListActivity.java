package cat.ppicas.cleanarch.activity;

import android.app.Activity;
import android.os.Bundle;

import cat.ppicas.cleanarch.fragment.FruitListFragment;

public class FruitListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new FruitListFragment())
                    .commit();
        }
    }
}
