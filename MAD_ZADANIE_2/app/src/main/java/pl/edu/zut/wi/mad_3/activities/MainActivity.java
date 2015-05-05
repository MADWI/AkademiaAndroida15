package pl.edu.zut.wi.mad_3.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import pl.edu.zut.wi.mad_3.R;
import pl.edu.zut.wi.mad_3.fragments.FragmentMain;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.container_main, new FragmentMain()).commit();
        }

    }

}
