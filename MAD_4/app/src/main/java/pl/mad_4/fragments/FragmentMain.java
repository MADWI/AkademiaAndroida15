package pl.mad_4.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.mad_4.R;

/**
 * Created by rafalmielnik on 29.05.15.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    private Button buttonSharedPreferences;
    private Button buttonFiles;
    private Button buttonDatabase;

    public static Fragment newInstance() {
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        buttonSharedPreferences = (Button) view.findViewById(R.id.button_shared_preferences);
        buttonSharedPreferences.setOnClickListener(this);

        buttonFiles = (Button) view.findViewById(R.id.button_files);
        buttonFiles.setOnClickListener(this);

        buttonDatabase = (Button) view.findViewById(R.id.button_database);
        buttonDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.button_shared_preferences:
                fragment = FragmentSharedPreferences.newInstance();
                break;
            case R.id.button_files:
                fragment = FragmentFiles.newInstance();
                break;
            case R.id.button_database:
                fragment = FragmentDatabase.newInstance();
                break;
        }

        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
        }

    }

}
