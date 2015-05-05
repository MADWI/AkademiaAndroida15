package pl.edu.zut.wi.mad_3.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.edu.zut.wi.mad_3.R;

/**
 * Created by rafalmielnik on 21.04.15.
 */
public class FragmentA extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {

    }
}
