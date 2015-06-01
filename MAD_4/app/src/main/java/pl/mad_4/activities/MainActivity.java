package pl.mad_4.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import pl.mad_4.R;
import pl.mad_4.fragments.FragmentMain;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.container, FragmentMain.newInstance()).commit();
        }
    }


}
