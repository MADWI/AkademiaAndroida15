package pl.edu.zut.wi.mad_zadanie_1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    /**
     * Link do cyklu życia aktywności:
     * http://developer.android.com/reference/android/app/Activity.html
     */

    private TextView textViewLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLifecycle = (TextView) findViewById(R.id.text_view_lifecycle);
        appendToLifecycle("onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        appendToLifecycle("onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        appendToLifecycle("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        appendToLifecycle("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        appendToLifecycle("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        appendToLifecycle("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appendToLifecycle("onDestroy()");
    }

    private void appendToLifecycle(String methodName) {
        textViewLifecycle.setText(textViewLifecycle.getText().toString() + methodName + "\n");
    }


}
